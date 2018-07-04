package papacapstone.DatabaseTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import papacapstone.Clients.Admin;
import papacapstone.EncryptionTools.PasswordEncryptor;

/**
 * 
 * Contains stored procedures (SQL queries) which interact with the "Admins"
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
public class AdminQueryManager extends DatabaseController {
	private PreparedStatement authenticateAdminUserStatement;
	private PreparedStatement registerNewAdminUserStatement;
	private PreparedStatement removeUserStatement;
	private PreparedStatement removeGameStatement;
	private PreparedStatement removeAllGamesByAuthorStatement;

	/**
	 * Constructor calls helper method, which connects to DB and prepares stored
	 * procedures
	 */
	public AdminQueryManager() {
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
		authenticateAdminUserStatement = conn
				.prepareStatement("SELECT * " + "FROM Admins " + "WHERE username_AD=? AND password_AD=?");

		// Register query
		registerNewAdminUserStatement = conn.prepareStatement(
				"INSERT into Admins( username_AD, password_AD, emailAddress_Ad ) " + "VALUES(?, ?, ?)");

		// Remove user query
		removeUserStatement = conn.prepareStatement("DELETE FROM users " + "WHERE username=?");

		// Remove single game from database
		removeGameStatement = conn.prepareStatement("DELETE FROM filedata " + "WHERE url=?");

		// Remove all games by a particular author
		removeAllGamesByAuthorStatement = conn.prepareStatement("DELETE FROM filedata " + "WHERE author=?");
	}// end of initPreparedStatements()

	/**
	 * Adds new Admin to database.
	 */
	public void registerNewAdminUser(String username, String password, String email) throws SQLException {
		String encryptedPwd = PasswordEncryptor.encrypt(password);

		registerNewAdminUserStatement.setString(1, username);
		registerNewAdminUserStatement.setString(2, encryptedPwd);
		registerNewAdminUserStatement.setString(3, email);

		registerNewAdminUserStatement.executeUpdate();
	}

	/**
	 * Authenticates an Admin's credentials. Returns matching Admin object if
	 * exists.
	 */
	public Admin authenticateAdminUser(String username, String password) throws SQLException {
		Admin adminuser = null;

		authenticateAdminUserStatement.setString(1, username);
		authenticateAdminUserStatement.setString(2, password);

		ResultSet rs = authenticateAdminUserStatement.executeQuery();
		if (rs.next()) {
			adminuser = new Admin(rs.getString("username_AD"), rs.getString("password_AD"),
					rs.getString("emailAddress_AD"));
		}

		return adminuser;
	}

	/**
	 * Removes a user from the DB.
	 */
	public void removeUser(String username) throws SQLException {
		removeUserStatement.setString(1, username);
		removeUserStatement.executeUpdate();
	}// end of removeAdmin

	/**
	 * Deletes game from the database.
	 */
	public void deleteGame(int url) throws SQLException {
		removeGameStatement.setInt(1, url);
		removeGameStatement.executeUpdate();
	}

	/**
	 * Deletes all games by the same author from database.
	 */
	public void deleteAllGamesByAuthor(String author) throws SQLException {
		removeAllGamesByAuthorStatement.setString(1, author);
		removeAllGamesByAuthorStatement.executeUpdate();
	}

}// end of class