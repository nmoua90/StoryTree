package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.DatabaseTools.UserQueryManager;

/**
 * Controller which resets a user's password.
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
public class PasswordResetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Reset a user's password based on HTML form fields.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String newPass = (String) request.getParameter("newPassword");
		String newPassCheck = (String) request.getParameter("newPasswordCheck");

		String mainUserID = (String) request.getParameter("userName");
		String activeUserID = (String) request.getParameter("userNameActive");

		boolean userIsActive = (mainUserID == "");

		if (userIsActive) {
			mainUserID = activeUserID;
		}

		boolean passwordsAreEqual = compareTo(newPass, newPassCheck);

		try {
			if (passwordsAreEqual) {
				UserQueryManager uqMgr = new UserQueryManager();
				uqMgr.resetPassword(newPass, mainUserID);
				uqMgr.closeConnection();

				String displayMessage = "You have changed your password! May you enjoy the Storytree experience!";
				request.setAttribute("displayMessage", displayMessage);
				routeToNextPage(request, response, "/SuccessfulRegistration");
			}
		} catch (SQLException e) {
			routeToNextPage(request, response, "/ResetPassword");
		}

	}// end of doPost()

	/**
	 * Helper method which compares if two Strings are equal (used to check that two
	 * passwords are the same).
	 */
	private boolean compareTo(String one, String two) {
		return one.compareTo(two) == 0;
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