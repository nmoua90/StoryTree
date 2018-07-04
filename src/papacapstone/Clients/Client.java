package papacapstone.Clients;

/**
 * 
 * Abstract class which declares reusable fields/methods for children classes:
 * (Admin, User)
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
public abstract class Client {
	protected String username;
	protected String password;
	protected String emailAddress;

	/**
	 * Disallow default constructor
	 */
	private Client() {
	}

	/**
	 * Simple constructor which sets instance variables.
	 * 
	 * @param username
	 * @param password
	 * @param emailAddress
	 */
	public Client(String username, String password, String emailAddress) {
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	/**
	 * Retrieves username of client.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username of client.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retrieves client's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets client's password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves email address of Client.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets email address of Client.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}// end of class