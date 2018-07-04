package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.DatabaseTools.UserQueryManager;
import papacapstone.ServerTools.MailSender;

/**
 * Controller that sends account recovery email to a user.
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
public class EmailRecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieves Activation Link for a user, and sends account recovery email with
	 * that activation link in the url.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailAddress = request.getParameter("newEmail");

		try {
			UserQueryManager uqMgr = new UserQueryManager();
			String regLink = uqMgr.getActivationLink(emailAddress);
			uqMgr.closeConnection();

			// Send a registration email to the new user
			MailSender mailer = new MailSender(emailAddress);
			mailer.prepareRegisrationEmail(regLink);
			mailer.send();

			request.setAttribute("emailAddress", emailAddress);

			routeToNextPage(request, response, "/EmailSent");
		} catch (SQLException e) {
			// No matching email in system
			routeToNextPage(request, response, "/404");
		}

	}// end of doPost

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