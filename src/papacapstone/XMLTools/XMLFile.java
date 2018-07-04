package papacapstone.XMLTools;

/**
 * Stores the metadata fields (Game Title and Author) of an XML File.
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
public class XMLFile {
	private String gameTitle;
	private String author;

	/**
	 * Disallow default constructor
	 */
	private XMLFile() {
	}

	/**
	 * Constructor sets instance variables
	 */
	XMLFile(String gameTitle, String author) {
		this.gameTitle = gameTitle;
		this.author = author;
	}

	/**
	 * Retrieves game title within an XML file
	 */
	public String getGameTitle() {
		return gameTitle;
	}

	/**
	 * Sets game title within an XML file
	 */
	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	/**
	 * Gets author within an XML file
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets author within an XML file
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

}// end of class