package papacapstone.DatabaseTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import papacapstone.Clients.User;
import papacapstone.EncryptionTools.PasswordEncryptor;
import papacapstone.ServerTools.TimeKeeper;

/**
 * 
 * Contains stored procedures (SQL queries) which interact with the "Users"
 * Table (which is stored within a SQL DB). Child of Abstract class:
 * DatabaseController.
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
public class UserQueryManager extends DatabaseController {
	private PreparedStatement authenticateUser;
	private PreparedStatement registerNewUser;
	private PreparedStatement checkIfAccountActive;
	private PreparedStatement activateAccount;
	private PreparedStatement getActivationLinkBasedOnEmail;
	private PreparedStatement getUserNameBasedOnActivLink;
	private PreparedStatement resetPassword;
	private PreparedStatement deleteUser;
	private PreparedStatement suspendUser;

	/**
	 * Constructor calls helper method, which connects to DB and prepares stored
	 * procedures
	 */
	public UserQueryManager() {
		connectToDBAndPrepareStatements();
	}

	/**
	 * Helper method, which connects to the DB and prepares all stored procedures
	 */
	private void connectToDBAndPrepareStatements() {
		try {
			if (!(connectionExists())) {
				connectToDB();
			}
			initPreparedStatements();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prepares all stored procedures
	 */
	@Override
	protected void initPreparedStatements() throws SQLException {
		// Login query
		authenticateUser = conn.prepareStatement("SELECT * " + "FROM Users " + "WHERE username=? AND password=?");

		// Register query
		registerNewUser = conn.prepareStatement(
				"INSERT into Users( username, password, emailAddress, activationLink, registrationDate ) "
						+ "VALUES(?, ?, ?, ?, ?)");

		// Check activated status query
		checkIfAccountActive = conn.prepareStatement("SELECT active " + "FROM Users " + "WHERE username=?");

		// Activate an account based on an activation link value
		activateAccount = conn.prepareStatement("UPDATE Users " + "SET active=? " + "WHERE activationLink=?");

		// Get activation link based on email
		getActivationLinkBasedOnEmail = conn
				.prepareStatement("SELECT activationLink " + "FROM Users " + "WHERE emailAddress=?");

		// Retrieve username based on activation link
		getUserNameBasedOnActivLink = conn
				.prepareStatement("SELECT username " + "FROM Users " + "WHERE activationLink=?");

		// Reset password based on activation link
		resetPassword = conn.prepareStatement("UPDATE Users " + "SET password=? " + "WHERE username=?");

		// Delete a user based on username
		deleteUser = conn.prepareStatement("DELETE FROM users " + "WHERE username = ?");

		// Suspend a user based on username
		suspendUser = conn.prepareStatement("UPDATE Users " + "SET active=false " + "WHERE username = ?");

	}// end of initPreparedStatements()

	/**
	 * Delete a user from the database.
	 */
	public void deleteUser(String userName) throws SQLException {
		deleteUser.setString(1, userName);
		deleteUser.executeUpdate();
	}

	/**
	 * Sets a user's activation status to suspended.
	 */
	public void suspendUser(String userName) throws SQLException {
		suspendUser.setString(1, userName);
		suspendUser.executeUpdate();
	}

	/**
	 * Resets a username's password by entering a new password.
	 */
	public void resetPassword(String newPassword, String userName) throws SQLException {
		String hashedPassword = PasswordEncryptor.encrypt(newPassword);

		resetPassword.setString(1, hashedPassword);
		resetPassword.setString(2, userName);
		resetPassword.executeUpdate();
	}

	/**
	 * Retrieves a username based on an input email.
	 */
	public String getUsername(String actID) throws SQLException {
		getUserNameBasedOnActivLink.setString(1, actID);
		ResultSet result = getUserNameBasedOnActivLink.executeQuery();
		result.next();

		return result.getString("username");
	}

	/**
	 * Retrieves an activation link based on user email.
	 */
	public String getActivationLink(String email) throws SQLException {
		getActivationLinkBasedOnEmail.setString(1, email);
		ResultSet result = getActivationLinkBasedOnEmail.executeQuery();
		result.next();

		return result.getString("activationLink");
	}

	/**
	 * Activates an account for a user, based on an activationID.
	 */
	public void activateAccount(String actID) throws SQLException {
		activateAccount.setBoolean(1, true);
		activateAccount.setString(2, actID);
		activateAccount.executeUpdate();
	}

	/**
	 * Checks if user is activated.
	 */
	public boolean checkIfUserActivated(String username) throws SQLException {
		checkIfAccountActive.setString(1, username);

		ResultSet result = checkIfAccountActive.executeQuery();
		result.next();

		// Get value from "active" column
		String userStatus = result.getString("active");
		int us = Integer.valueOf(userStatus);

		// If user is active
		if (us == 1) {
			return true;
		}

		// If not active
		return false;
	}

	/**
	 * Adds a new user to database.
	 */
	public void registerNewUser(String username, String password, String email, String regLink) throws SQLException {
		String encryptedPwd = PasswordEncryptor.encrypt(password);

		registerNewUser.setString(1, username);
		registerNewUser.setString(2, encryptedPwd);
		registerNewUser.setString(3, email);
		registerNewUser.setString(4, regLink);
		registerNewUser.setObject(5, TimeKeeper.getCurrentDate());
		registerNewUser.executeUpdate();
	}

	/**
	 * Authenticates the credentials of a user.
	 */
	public User authenticateUser(String username, String password) throws SQLException {
		User user = null;

		// Add parameters to the ?'s in the preparedstatement
		authenticateUser.setString(1, username);
		authenticateUser.setString(2, password);

		ResultSet rs = authenticateUser.executeQuery();
		if (rs.next()) {
			user = new User(rs.getString("username"), rs.getString("password"), rs.getString("emailAddress"));
		}

		return user;
	}

}// end of class