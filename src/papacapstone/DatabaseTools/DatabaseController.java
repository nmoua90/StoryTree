package papacapstone.DatabaseTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import papacapstone.ServerTools.Credentials;

/**
 * An abstract class which provides re-usability and scalability for children
 * classes. Children classes can extend functionality via implementing abstract
 * methods, which will contain stored procedures (SQL statements).
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
public abstract class DatabaseController<T> {
	protected Connection conn;

	/**
	 * Connects to the Database.
	 */
	public void connectToDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/StoryTree?verifyServerCertificate=false&useSSL=true",
				Credentials.getDBUsername(), Credentials.getDBPassword());
	}

	/**
	 * Checks to see if a Database connection already exists.
	 */
	protected boolean connectionExists() {
		return !(conn == null);
	}

	/**
	 * To be implemented by Children classes: This method prepares stored
	 * procedures.
	 */
	protected abstract void initPreparedStatements() throws SQLException;

	/**
	 * Closes the DB connection.
	 */
	public void closeConnection() throws SQLException {
		conn.close();
	}

}// end of class