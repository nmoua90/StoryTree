package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.DatabaseTools.UserQueryManager;
import papacapstone.EncryptionTools.RandomStringGenerator;
import papacapstone.ServerTools.MailSender;

/**
 * Controller which registers a new user.
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
@WebServlet(description = "A controller for handling new user creation", urlPatterns = { "/RegisterUser" })
public class RegisterUserController extends HttpServlet {
	private String url;
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieves HTML input forms and registers a new user. If registration
	 * successful, sends an activation email to the new user.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get input fields
		String username = request.getParameter("newUserName");
		String password = request.getParameter("newPassword");
		String emailAddress = request.getParameter("newEmail");

		try {
			// Check if registration details are valid
			UserQueryManager uqm = new UserQueryManager();

			// Create registration link for new user
			RandomStringGenerator rsGenerator = new RandomStringGenerator();
			String regLink = rsGenerator.nextString();

			uqm.registerNewUser(username, password, emailAddress, regLink);
			uqm.closeConnection();

			// Send a registration email to the new user
			MailSender mailer = new MailSender(emailAddress);
			mailer.prepareRegisrationEmail(regLink);
			mailer.send();

			// Redirect the user onto the login page
			String displayMessage = "Success! You are now registered, but your account is NOT YET activated.";
			String infoMessage = "To activate your account, check: " + emailAddress
					+ " and click on the activation link.";

			request.setAttribute("displayMessage", displayMessage);
			request.setAttribute("infoMessage", infoMessage);

			routeToNextPage(request, response, "/SuccessfulRegistration");
		} catch (SQLException e) {
			// Refresh the page with an error message
			String errorMessage = "Username/Email already in our system.";
			request.setAttribute("errorMessage", errorMessage);
			routeToNextPage(request, response, "/Register");
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