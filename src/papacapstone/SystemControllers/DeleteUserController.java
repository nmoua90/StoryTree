package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.DatabaseTools.UserQueryManager;

/**
 * Controller which deletes a User's Account.
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
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Deletes a User's Account (Accessed from User Control Panel page)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get current user
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("curUserRef");

		// Disallow guests on the forwarded page
		if (userName == null) {
			routeToNextPage(request, response, "/404");
		}

		try {
			UserQueryManager uqMgr = new UserQueryManager();
			uqMgr.deleteUser(userName);
			uqMgr.closeConnection();

			String message = "YOU HAVE BEEN DELETED.";
			request.setAttribute("message", message);
			routeToNextPage(request, response, "/Splash");
		} catch (SQLException e) {
			routeToNextPage(request, response, "/404");
		}
	}// end of doPost()

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