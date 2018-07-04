package papacapstone.GameLogic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * The model interface contains all Game Data. It provides methods to retrieve
 * Game Data, and knows how to search through Game Data.
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
public interface Model<T> {

	/**
	 * Prepare game data for processing.
	 */
	public void prepareData(File xmlFile) throws SAXException, IOException, ParserConfigurationException;

	/**
	 * Retrieve game data.
	 */
	public T getData();

	/**
	 * Search game data for a specific scene.
	 */
	public Scene searchForScene(String id);

}// end of class