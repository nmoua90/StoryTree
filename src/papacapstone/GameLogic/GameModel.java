package papacapstone.GameLogic;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import papacapstone.XMLTools.XMLParser;

/**
 * 
 * Contains all Game Data within a Hashmap. Acquires Game Data by calling an
 * XMLParser which returns data. Implements the Model interface.
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
public class GameModel implements Model {
	private XMLParser parser;
	private String gameTitle;
	private Map<String, Scene> data;

	/**
	 * Standard constructor instantiates an XML Parser
	 */
	public GameModel() {
		parser = new XMLParser();
	}

	/**
	 * Prepares Game Data based on the parsing of an XML File.
	 */
	public void prepareData(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
		parser.parseFile(xmlFile);
		data = parser.getData();
		gameTitle = parser.getGameTitle();
	}

	/**
	 * Sets a HashMap containing all Game Data.
	 */
	public void setData(Map<String, Scene> data) {
		this.data = data;
	}

	/**
	 * Retrieves a HashMap containing all Game Data.
	 */
	public Map<String, Scene> getData() {
		return data;
	}

	/**
	 * Retrieves game title for the current game.
	 */
	public String getGameTitle() {
		return gameTitle;
	}

	/**
	 * Searches the HashMap for a specific Scene ID.
	 */
	public Scene searchForScene(String sceneID) {
		return data.get(sceneID);
	}

}// end of class