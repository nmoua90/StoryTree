package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import papacapstone.DatabaseTools.AdminQueryManager;

/**
 * 
 * This class takes care of business logic on the Admin Suite page.
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
 *
 */
public class AdminRemoveUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		AdminQueryManager aqm = new AdminQueryManager();
		String getUser = request.getParameter("enteredUserName");

		try {
			aqm.removeUser(getUser);
			aqm.closeConnection();

			String successMessage = "Success! " + getUser + " has been removed";
			request.setAttribute("successMessage", successMessage);
			routeToNextPage(request, response, "/Admin_RemoveUser");
		} catch (SQLException e) {
			String errorMessage = "Something Went Wrong";
			request.setAttribute("errorMessage", errorMessage);
			routeToNextPage(request, response, "/Admin_RemoveUser");
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