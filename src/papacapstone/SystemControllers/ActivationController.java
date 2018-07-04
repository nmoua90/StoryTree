package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.DatabaseTools.UserQueryManager;

/**
 * Controller which activates a user's account.
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
public class ActivationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieves activation id form URL and attempts to activate a user's account
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get activation id param from url
		String activationLink = request.getParameter("activationValue");

		// Activate account with this activation id
		UserQueryManager queryMgr = new UserQueryManager();

		try {
			queryMgr.activateAccount(activationLink);
			queryMgr.closeConnection();

			String displayMessage = "Success! Your account is activated! Have fun playing our games! Woo!!!!";
			request.setAttribute("displayMessage", displayMessage);

			routeToNextPage(request, response, "/SuccessfulRegistration");
		} catch (SQLException e) {
			String errorMessage = "Sorry, but we couldn't activate your account. Are you sure you clicked the right verification link?";
			request.setAttribute("errorMessage", errorMessage);

			routeToNextPage(request, response, "/RecoveryPage");
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
	}

}// end of class