package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.Clients.Admin;
import papacapstone.DatabaseTools.AdminQueryManager;
import papacapstone.EncryptionTools.PasswordEncryptor;

/**
 * Controller which authenticates an Admin within the system.
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
@WebServlet(description = "A controller for handling user logins", urlPatterns = { "/AdminLogger" })
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int adminLoginAttempts;
	private String url;

	public AdminLoginController() {
	}

	/**
	 * Logs Admin account into session if authentication passes
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get our current session
		HttpSession adminSession = request.getSession();

		// get the number of logins
		if (adminSession.getAttribute("loginAttempts") == null) {
			adminLoginAttempts = 0;
		}

		try {
			if (adminLoginAttempts > 3) {
				String errorMessageAD = "Error: Number of Login Attempts Exceeded";
				request.setAttribute("errorMessageAD", errorMessageAD);
				url = "AdminLogin";
			} else {
				String username = request.getParameter("adminUsername");
				String password = request.getParameter("adminPassword");

				String encryptedPassword = PasswordEncryptor.encrypt(password);

				// UserQueryManager uh = new UserQueryManager();
				AdminQueryManager aqm = new AdminQueryManager();
				Admin adminUser = aqm.authenticateAdminUser(username, encryptedPassword);
				aqm.closeConnection();

				// we've found a user that matches the credentials
				if (adminUser != null) {
					// invalidate current session, then get new session for our user (combats:
					// session hijacking)
					adminSession.invalidate();
					adminSession = request.getSession(true);
					adminSession.setAttribute("adminUser", adminUser);
					routeToNextPage(request, response, "/AdminHome");
				}

				else {
					String errorMessageAD = "Error: Unrecognized Username or Password<br>Login attempts remaining: "
							+ (3 - (adminLoginAttempts));
					request.setAttribute("errorMessage", errorMessageAD);
					// track login attempts (combats: brute force attacks)
					adminSession.setAttribute("loginAttempts", adminLoginAttempts++);
					routeToNextPage(request, response, "/AdminLogin");
				}
			} // end of else on line 47
		} catch (SQLException e) {
			e.printStackTrace(); // TODO: Redirect to correct page
		}
	}// end of DoPost()

	/**
	 * Logs the admin out.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User has clicked the logout link
		HttpSession adminSession = request.getSession();

		// check to make sure we've clicked link
		if (request.getParameter("logout") != null) {
			// logout and redirect to frontpage
			adminSession.invalidate();

		}
		// forward our request along
		routeToNextPage(request, response, "/AdminLogin");

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