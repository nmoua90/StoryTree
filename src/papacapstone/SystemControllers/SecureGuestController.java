package papacapstone.SystemControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller which disallows unathenticated guests to use certain features
 * within the website.
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
public class SecureGuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Attempts to get the current client info for some action, and locks out the
	 * client (prior to allowing some page load) if they are not authenticated.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get current user
		HttpSession session = request.getSession();
		String curUser = (String) session.getAttribute("curUserRef");

		// Disallow guests from uploading games
		if (curUser == null) {
			routeToNextPage(request, response, "/404");
		}

		String nextPage = request.getParameter("routeTo");
		routeToNextPage(request, response, "/" + nextPage);
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