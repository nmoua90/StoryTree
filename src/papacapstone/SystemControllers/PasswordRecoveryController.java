package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.DatabaseTools.UserQueryManager;

/**
 * Controller which parses an activation link field from the URL, and forwards
 * to the next Controller.
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
public class PasswordRecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieves Activation ID from URL and forwards it to next Controller
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the activation link
		String actLink = request.getParameter("activationValue");

		try {
			// Get username based on act. link
			UserQueryManager uqMgr = new UserQueryManager();
			String userName = uqMgr.getUsername(actLink);
			uqMgr.closeConnection();

			// Set username for next page
			request.setAttribute("userName", userName);

			// forward
			routeToNextPage(request, response, "/ResetPassword");
		} catch (SQLException e) {
			e.printStackTrace(); // TODO: Redirect to proper page
		}

	}// end of doPost

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