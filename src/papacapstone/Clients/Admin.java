package papacapstone.Clients;

/**
 * 
 * Simple Admin class: a child of Client.
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
public class Admin extends Client {

	/**
	 * Invokes Client's constructor.
	 * 
	 * @param username
	 * @param password
	 * @param emailAddress
	 */
	public Admin(String username, String password, String emailAddress) {
		super(username, password, emailAddress);
	}

}// end of class