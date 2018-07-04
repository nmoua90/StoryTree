package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.Clients.User;
import papacapstone.DatabaseTools.UserQueryManager;
import papacapstone.EncryptionTools.PasswordEncryptor;

/**
 * Controller that authenticates user credentials, and creates HTTP sessions for
 * those who are authenticated.
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
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int loginAttempts;

	/**
	 * Default constructor calls HttpServlet()
	 */
	public UserLoginController() {
		super();
	}

	/**
	 * Attempts to authenticate a user and create an HTTP Session if user is
	 * authenticated.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get our current session
		HttpSession session = request.getSession();

		// get the number of logins
		if (session.getAttribute("loginAttempts") == null) {
			loginAttempts = 0;
		}

		try {
			if (loginAttempts > 3) {
				String errorMessage = "Error: Number of Login Attempts Exceeded";
				request.setAttribute("errorMessage", errorMessage);
			} else {
				String username = request.getParameter("username");
				String password = request.getParameter("password");

				String encryptedPassword = PasswordEncryptor.encrypt(password);

				UserQueryManager uh = new UserQueryManager();
				User user = uh.authenticateUser(username, encryptedPassword);

				// we've found a user that matches the credentials
				if (user != null) {

					// check that user's account is currently activated
					if (uh.checkIfUserActivated(username)) {

						// invalidate current session, then get new session for our user (combats:
						// session hijacking)
						session.invalidate();
						session = request.getSession(true);
						session.setAttribute("user", user);
						session.setAttribute("curUserRef", username);

						getServletContext().getRequestDispatcher("/UserPanel").forward(request, response);

						// user is not activated
					} else {
						String errorMessage = "Error: Account not activated.";
						request.setAttribute("errorMessage", errorMessage);
						getServletContext().getRequestDispatcher("/UserLogin").forward(request, response);
					}
				}

				else {
					String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "
							+ (3 - (loginAttempts));
					request.setAttribute("errorMessage", errorMessage);
					// track login attempts (combats: brute force attacks)
					session.setAttribute("loginAttempts", loginAttempts++);

					getServletContext().getRequestDispatcher("/UserLogin").forward(request, response);
				}

				uh.closeConnection();

			} // end of else on line 48
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// end of doPost()

	/**
	 * Handles invalidating an HTTP Session when the User logs out
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// User has clicked the logout link
		HttpSession session = request.getSession();

		// check to make sure we've clicked link
		if (request.getParameter("logout") != null) {
			session.invalidate();
		}

		routeToNextPage(request, response, "/UserLogin");
	}// end of doGet()

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