package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.DatabaseTools.AdminQueryManager;
import papacapstone.FileSystemTools.FileManager;

/**
 * This class handles the logic of removing a game from the
 * database. It's called from Admin_DeleteGame.jsp
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
public class AdminDeleteGameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AdminQueryManager aqm = new AdminQueryManager();
		FileManager fm = new FileManager();

		String getURL = request.getParameter("enteredGameID");
		String getTitle = request.getParameter("gameTitleToDelete");

		try {
			aqm.deleteGame(Integer.parseInt(getURL));
			fm.deleteFileFromPermanentFolder(getURL + ".xml");

			aqm.closeConnection();

			String successMessage = "Success! " + getTitle + " has been deleted";
			request.setAttribute("successMessage", successMessage);

			routeToNextPage(request, response, "/Admin_DeleteGame");
		} catch (SQLException | IOException e) {
			String errorMessage = "Sorry, something Went Awry. Maybe try that again";
			request.setAttribute("errorMessage", errorMessage);
			routeToNextPage(request, response, "/"); // TODO: Where are we routing?
		}

	}

	/**
	 * Helper method that routes to the next page.
	 */
	private void routeToNextPage(HttpServletRequest request, HttpServletResponse response, String nextPage) {
		try {
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} catch (IOException | ServletException | NullPointerException e) {
			e.printStackTrace();
		}
	}

}// end of class