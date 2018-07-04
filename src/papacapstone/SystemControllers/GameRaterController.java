package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.DatabaseTools.FileDBQueryManager;

/**
 * 
 * Updates Database based on Rating of a Game.
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
public class GameRaterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			// Get the current session
			HttpSession session = req.getSession();

			// Get relevant fields
			int gameScore = Integer.valueOf(req.getParameter("gameScore"));
			int gameID = Integer.valueOf((String) session.getAttribute("curGameID"));
			String xmlFilePathLocation = (String) session.getAttribute("curGameURL");

			// Run stored procedures
			FileDBQueryManager fileDBMgr = new FileDBQueryManager();
			fileDBMgr.updateGameScore(gameID, gameScore);
			fileDBMgr.closeConnection();

			// Route to Refreshed Page
			session.setAttribute("curGameID", String.valueOf(gameID));
			session.setAttribute("curGameURL", xmlFilePathLocation);
			routeToNextPage(req, res, "/PlayGameLoadPage");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of doPost()

	/**
	 * Helper method that routes to the next page.
	 */
	private void routeToNextPage(HttpServletRequest request, HttpServletResponse response, String nextPage) {
		try {
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}// end of routeToNextPage()

}// end of class