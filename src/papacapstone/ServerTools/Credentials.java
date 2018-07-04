package papacapstone.ServerTools;

import java.io.File;

/**
 * Credentials that connect to the Database.
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
public class Credentials {
	/** Username for Database */
	private final static String dbUsername = "admin";
	/** Password for Database */
	private final static String dbPassword = "admin";
	/** Username for Email Server */
	private final static String clientAddress = "omittedEmail@gmail.com";
	/** Password for Email Server */
	private final static String clientPassword = "omittedPassword";
	/** Perm. folder is location where valid files are stored. */
	private static final String permanentUploadFolder = "C:\\Users\\Nhia\\StoryGames\\";
	/** XSD File which checks constraints of User Uploaded XMLs */
	private static final File schemaFile = new File("C:\\Users\\Nhia\\Desktop\\XML Files\\MasterSchema.xsd");

	/**
	 * Retrieve location of XSD Schema File.
	 */
	public static File getSchemafile() {
		return schemaFile;
	}

	/**
	 * Retrieve DB Username
	 */
	public static String getDBUsername() {
		return dbUsername;
	}

	/**
	 * Retrieve DB Password
	 */
	public static String getDBPassword() {
		return dbPassword;
	}

	/**
	 * Retrieve Email Server Username
	 */
	public static String getEmailServerAddress() {
		return clientAddress;
	}

	/**
	 * Retrieve Email Server Password
	 */
	public static String getEmailServerPassword() {
		return clientPassword;
	}

	/**
	 * Retrieve permanent folder
	 */
	public static String getPermanentuploadfolder() {
		return permanentUploadFolder;
	}

}// end of class