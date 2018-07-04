package papacapstone.DatabaseTools;

/**
 * 
 * This class stores metadata of a file.
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
public class FileMetadata {
	private int gameID;
	private String title;
	private String author;
	private int classID;
	private String checksum;

	/**
	 * Disallow default constructor
	 */
	private FileMetadata() {
	}

	/**
	 * Standard constructor sets instance variables.
	 * 
	 * @param url
	 * @param title
	 * @param author
	 * @param classID
	 * @param checksum
	 */
	public FileMetadata(int gameID, String title, String author, int classID, String checksum) {
		this.gameID = gameID;
		this.title = title;
		this.author = author;
		this.classID = classID;
		this.checksum = checksum;
	}

	/**
	 * Retrieves GameID of a file.
	 */
	public int getGameID() {
		return gameID;
	}

	/**
	 * Sets GameID of a file.
	 */
	public void setGameID(int url) {
		this.gameID = url;
	}

	/**
	 * Retrieves game title from a file.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets game title from a file.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retrieves author of a file.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets author of a file.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Retrieves Class ID from a file.
	 */
	public int getClassID() {
		return classID;
	}

	/**
	 * Sets Class ID from a file.
	 */
	public void setClassID(int classID) {
		this.classID = classID;
	}

	/**
	 * Retrieves checksum of a file.
	 */
	public String getChecksum() {
		return checksum;
	}

	/**
	 * Sets checksum of a file.
	 */
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

}// end of class