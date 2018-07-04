package papacapstone.FileSystemTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import papacapstone.ServerTools.Credentials;

/**
 * 
 * Manages files within the file system.
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
 *
 */
public class FileManager {
	/** Perm. folder is location where valid files are stored. */
	private static final String permanentUploadFolder = Credentials.getPermanentuploadfolder();

	/**
	 * Returns the absolute path of a file within the permanent folder.
	 */
	public String getURLOfFileWithinPermanentFolder(String gameID) {
		return permanentUploadFolder + "\\" + gameID + ".xml";
	}

	/**
	 * Retrieves file from the file system based on an absolute path.
	 */
	public File retrieveFile(String url) throws FileNotFoundException, InputMismatchException {
		File f = new File(url);
		if (!f.exists()) {
			throw new FileNotFoundException();
		} else if (!f.getName().toLowerCase().endsWith(".xml")) {
			throw new InputMismatchException();
		}
		return f;
	}

	/**
	 * Deletes a file from the permanent folder.
	 */
	public void deleteFileFromPermanentFolder(String fileName) throws IOException {
		Files.deleteIfExists(Paths.get(permanentUploadFolder + "\\" + fileName));
	}

}// end of FileManager class