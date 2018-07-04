package papacapstone.SystemControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.FileSystemTools.FileManager;

/**
 * Controller that retrieves an XML file from the File system, and forwards that
 * file to the next Controller.
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
public class FilesystemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieves an XML file from the file system based on gameID.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get the current session
		HttpSession session = request.getSession();

		// Get F.Mgr
		FileManager fileManager = new FileManager();

		// Get and set the entered game ID
		String curGameID = request.getParameter("enteredGameID");
		session.setAttribute("curGameID", curGameID);

		// Get and set the absolute path of an xml file
		String curGameURL = fileManager.getURLOfFileWithinPermanentFolder(curGameID);
		session.setAttribute("curGameURL", curGameURL);

		// Route to former page that just called
		String urlDirectory = request.getParameter("jsp");
		routeToNextPage(request, response, "/" + urlDirectory);
	}

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

}// end of class