package papacapstone.SystemControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.FileSystemTools.FileManager;

/**
 * Controller which routes a user to the GamePlay Controller.
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
public class DirectGameplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Takes GameID from url, and routes the user to the GamePlay Controller
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String curGameID = request.getParameter("curGameID");

		FileManager fMgr = new FileManager();
		String absPathOfGame = fMgr.getURLOfFileWithinPermanentFolder(curGameID);

		request.setAttribute("curGameID", curGameID);
		request.setAttribute("curGameURL", absPathOfGame);

		routeToNextPage(request, response, "/PlayGameLoadPage");
	}

	/**
	 * Helper method that routes to the next page.
	 * 
	 */
	private void routeToNextPage(HttpServletRequest request, HttpServletResponse response, String nextPage) {
		try {
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}// end of class