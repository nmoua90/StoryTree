package papacapstone.SystemControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.FileSystemTools.FileManager;

/**
 * 
 * Controller that downloads XML from file system via the corresponding Game ID.
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
public class GameFileDownloaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		FileManager fileManager = new FileManager();

		try {
			// Get link to current game
			String curGameLink = (String) session.getAttribute("curGameURL");

			// Retrieve file from filesystem
			File curFile = fileManager.retrieveFile(curGameLink);

			// Get data from file
			List fileData = createStringFromFileContents(curFile);

			// Get text data from file
			String fileContents = (String) fileData.get(0);
			String parsedFileContents = fileContents.replaceAll("\"", "@@@");

			// Store text data temporarily for usage on the editor page
			request.setAttribute("dataFromFile", parsedFileContents);

			// Get count of scenes from file
			int numScenes = (Integer) fileData.get(1);

			// Store scene count data temporarily for usage on the editor page
			request.setAttribute("numScenes", numScenes);

			// Redirect to editor
			routeToNextPage(request, response, "/NhiaEditor");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}// end of doPost()

	/**
	 * Read from the file and store all data into a string.
	 */
	private List createStringFromFileContents(File curFile) throws IOException {
		List metadata = new ArrayList();
		BufferedReader br = new BufferedReader(new FileReader(curFile));
		String fileContent = "";
		int numScenes = 0;

		String curLine = br.readLine();
		while (curLine != null) {
			// Port over the data
			fileContent += curLine + "\n";

			// Keep track of how many scenes there are
			if (curLine.contains("<SCENE sid =")) {
				numScenes++;
			}

			// Read next line
			curLine = br.readLine();
		}

		br.close();
		metadata.add(fileContent);
		metadata.add(numScenes);

		// Return file data & number of scenes
		return metadata;
	}// end of createStringFromFileContents

	/**
	 * Helper method that routes to the next page.
	 */
	private void routeToNextPage(HttpServletRequest request, HttpServletResponse response, String nextPage) {
		try {
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

} // end of class