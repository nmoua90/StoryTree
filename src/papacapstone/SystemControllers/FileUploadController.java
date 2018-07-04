package papacapstone.SystemControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;

import papacapstone.DatabaseTools.FileDBQueryManager;
import papacapstone.DatabaseTools.FileMetadata;
import papacapstone.EncryptionTools.MD5Generator;
import papacapstone.FileSystemTools.FileManager;
import papacapstone.ServerTools.Credentials;
import papacapstone.XMLTools.SchemaChecker;
import papacapstone.XMLTools.XMLParser;

/**
 * Controller that contains business logic for the uploading of a file to a
 * specific location within the file system. Sets file upload size limits, and
 * location where files are saved.
 * 
 * Copyright (C) 2018 -- Nhia Moua, Brian LeMoine, Evan Wall
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License 3.0 as published by the
 * Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License 3.0 for
 * more details.
 * 
 * You should have received a copy of the GNU Affero General Public License 3.0
 * along with this program. If not, see
 * https://www.gnu.org/licenses/agpl-3.0.txt
 * 
 * @author Papa Capstone
 */
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Upload set with following size restrictions */
	private static final int MAX_MEMORY_SIZE = 1000000; // 1mb file limit
	private static final int MAX_REQUEST_SIZE = 1000000;

	/* These Managers help retrieve file sys. metadata & query the DB */
	private FileManager fileManager = new FileManager();

	/* Listing of folder which contains games */
	private String permanentFolder = Credentials.getPermanentuploadfolder();

	/* Data retrieved from parsing */
	private FileItem curItemParsedFromFileChooser;
	private File curFile;

	/* Metadata retrieved from parsed files */
	private String fileAbsolutePath;
	private String gameTitleParsedFromXMLFile;
	private int classIDParsedFromXMLFile;
	private int curUniqueGameID;
	private String curChecksum;

	/**
	 * Uploads a file to the file system, and updates the status of the file
	 * database. Only uploads and updates the aforementioned fields if a file is
	 * valid for submission. Validity checking occurs within this method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get current user
		HttpSession session = request.getSession();
		FileDBQueryManager fileDBQueryManager = new FileDBQueryManager();
		String author = (String) session.getAttribute("curUserRef");

		// Disallow guests from uploading games
		if (author == null) {
			routeToNextPage(request, response, "/404");
		}

		// Go through File Upload Logic, catch SQL Exception if failure
		try {

			// Setup the data of the incoming file. Then save it to a temp directory.
			saveToFileSystem(fileDBQueryManager, request, response);

			// Check if schema XSD is validated
			if (!currentFilePassesSchemaCheck()) {
				routeToNextPage(request, response, "/404");
			}

			// Check that the file is valid
			if (!duplicateFileChecksumExists()) {
				// Create container to hold file metadata
				FileMetadata curFileMetadata = createFileMetaData(curItemParsedFromFileChooser, author);

				// Save file metadata to database
				saveFileMetadataToDatabase(fileDBQueryManager, curFileMetadata);

				// Display status of success to the user
				String successMessage = "Your file was successfully uploaded! The GAME ID is: " + curUniqueGameID;
				request.setAttribute("successMessage", successMessage);

				// Display another page after the upload finished
				routeToNextPage(request, response, "/UploadGame");
			} else {
				// If duplicate file, return the gameID belonging to the already existing file
				// and route
				String gameID = fileDBQueryManager.getIDWithChecksum(curChecksum);

				request.setAttribute("gameID", gameID);

				deleteCurGameFromFileSystem();

				routeToNextPage(request, response, "/DuplicateUpload");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
			deleteCurGameFromFileSystem();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			deleteCurGameFromFileSystem();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileDBQueryManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end of doPost()

	/**
	 * Deletes current uploaded file from file directory
	 */
	private void deleteCurGameFromFileSystem() throws IOException {
		FileManager fMgr = new FileManager();
		fMgr.deleteFileFromPermanentFolder(curUniqueGameID + ".xml");
	}

	/**
	 * Checks the current file for validity against an XSD Schema
	 */
	private boolean currentFilePassesSchemaCheck() throws IOException, SAXException {
		boolean passedXSDCheck = SchemaChecker.fileIsValidPerXSD(curFile);
		return passedXSDCheck;
	}

	/**
	 * Creates a StoryfileMetadata object from a user selected FileItem
	 */
	private FileMetadata createFileMetaData(FileItem item, String author) throws NullPointerException,
			NumberFormatException, SAXException, IOException, ParserConfigurationException {
		retrieveTitleAndClassIDFromXML(String.valueOf(curUniqueGameID));
		return new FileMetadata(curUniqueGameID, gameTitleParsedFromXMLFile, author, classIDParsedFromXMLFile,
				curChecksum);
	}

	/**
	 * Retrieves the Game Title and Class ID of an XML File
	 */
	private void retrieveTitleAndClassIDFromXML(String gameID) throws NullPointerException, NumberFormatException,
			SAXException, IOException, ParserConfigurationException {
		// Get the current File from the file system
		fileManager = new FileManager();
		String fileAbsPath = fileManager.getURLOfFileWithinPermanentFolder(gameID);
		File xmlFile = fileManager.retrieveFile(fileAbsPath);

		// Get the title & classID from the current file
		XMLParser xParser = new XMLParser();
		List metaData = xParser.parseFileForTitleAndClassID(xmlFile);

		// Set fields
		gameTitleParsedFromXMLFile = (String) metaData.get(0);
		classIDParsedFromXMLFile = (int) metaData.get(1);
	}

	/**
	 * Saves current File metadata to the DB
	 */
	private void saveFileMetadataToDatabase(FileDBQueryManager fdbm, FileMetadata currentFile) throws SQLException {
		fdbm.addNewGame(currentFile);
	}

	/**
	 * Calculates the checksum of the current file and queries the DB to see if this
	 * checksum already exists in the system
	 */
	private boolean duplicateFileChecksumExists() throws IOException, SQLException {
		curChecksum = MD5Generator.getChecksum(curFile);
		FileDBQueryManager fdbMgr = new FileDBQueryManager();
		boolean xmlAlreadyInSystem = fdbMgr.fileIsDuplicate(curChecksum);
		return xmlAlreadyInSystem;
	}

	/**
	 * Parses the incoming file, and saves it to the file system
	 */
	private void saveToFileSystem(FileDBQueryManager fdbm, HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, Exception, ServletException, IOException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// Don't continue trying to parse if the file is not multipart
		if (!isMultipart) {
			return;
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// Set size limit for a file upload
		upload.setFileSizeMax(MAX_MEMORY_SIZE);

		// Parse the request
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			curItemParsedFromFileChooser = (FileItem) iter.next();

			if (!curItemParsedFromFileChooser.isFormField()) {

				curUniqueGameID = createUniqueID(fdbm);

				fileAbsolutePath = permanentFolder + File.separator + curUniqueGameID + ".xml";

				// If this is removed, files are overwritten at the F.S. level without warning
				// duplicateFileNameExists(fileAbsolutePath);

				curFile = new File(fileAbsolutePath);

				// Save the file to upload directory
				curItemParsedFromFileChooser.write(curFile);
			}
		} // end of while

	}// end of saveFileToTempLocation()

	/**
	 * Checks to see if file already exists within the file system
	 */
	private boolean duplicateFileNameExists(String filePath) throws IOException {
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			throw new IOException("File with this GameID already in system.");
		}
		return false;
	}

	/**
	 * Queries the DB, and gets the correct unique gameID that a new game should
	 * have
	 */
	private int createUniqueID(FileDBQueryManager fdbm) throws SQLException {
		// Prepare your sql queries
		fdbm.connectToDBAndPrepareStatements();

		// Set the new id, based on sql query
		curUniqueGameID = fdbm.retrieveLastAddedGameID();

		return ++curUniqueGameID;
	}

	/**
	 * Helps route to next page
	 */
	private void routeToNextPage(HttpServletRequest request, HttpServletResponse response, String nextPage) {
		try {
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}// end of class