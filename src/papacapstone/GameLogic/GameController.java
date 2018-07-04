package papacapstone.GameLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import papacapstone.FileSystemTools.FileManager;
import papacapstone.SystemControllers.GameplayController;

/**
 * 
 * A controller which has access to the entire contents of one game's data. Has
 * the capability to keep track of the current scene being displayed.
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
public class GameController {
	private static Model model;
	private static Scene currentScene;

	/**
	 * Standard Constructor allowed for cases where a game is to be loaded, but the
	 * gameID is still unknown
	 */
	public GameController() {
	}

	/**
	 * Common Constructor initializes game data based on the URL of a file.
	 */
	public GameController(String xmlURL) throws SAXException, IOException, ParserConfigurationException {
		initGameData(xmlURL);
	}

	/**
	 * Initalizes game data based on the URL of a file.
	 */
	public void initGameData(String gameURL) throws SAXException, IOException, ParserConfigurationException {
		// Get a file
		FileManager fm = new FileManager();
		File curFile = fm.retrieveFile(gameURL);

		// Init data
		model = new GameModel();
		model.prepareData(curFile);

		// Set first scene to HOME SCENE
		loadNextScene("HOME");
	}

	/**
	 * Retrieves a model (the model contains a hashmap of all game data)
	 */
	public static Model getModel() {
		return model;
	}

	/**
	 * Retrieves the current Scene being played within the current game.
	 */
	public static Scene getCurrentScene() {
		return currentScene;
	}

	/**
	 * Loads the next scene for the current game, based on the next scene's ID.
	 */
	public static void loadNextScene(String id) throws NoSuchElementException {
		if (model.searchForScene(id) == null) {
			throw new NoSuchElementException("No Scene exists with that SID.");
		}
		currentScene = model.searchForScene(id);
	}

	/**
	 * Retrieves the title of the current game.
	 */
	public static String getCurrentTitle() {
		GameModel gModel = (GameModel) model;
		return gModel.getGameTitle();
	}

	/**
	 * Retrieves the sceneID of the current game.
	 */
	public static String getCurrentSceneSid() {
		String ans = currentScene.getSid();
		return (ans != null) ? ans : "";
	}

	/**
	 * Retrieves the number of paths for the current scene.
	 */
	public static int getCurrentSceneNumPaths() {
		return currentScene.getNumPaths();
	}

	/**
	 * Retrieves the Photo URL for the current scene.
	 */
	public static String getCurrentScenePhotoUrl() {
		String ans = currentScene.getPhoto();
		return (ans != null) ? ans : "";
	}

	/**
	 * Retrieves the body description for the current scene.
	 */
	public static String getCurrentSceneBodyDescription() {
		String ans = currentScene.getSceneDesc();
		return (ans != null) ? ans : "";
	}

	/**
	 * Retrieves Path1's linked SID for the current scene.
	 */
	public static String getCurrentScenePathLink1() {
		String[] curPaths = currentScene.getPathLinks();
		String ans = curPaths[0];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path2's linked SID for the current scene.
	 */
	public static String getCurrentScenePathLink2() {
		String[] curPaths = currentScene.getPathLinks();
		String ans = curPaths[1];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path3's linked SID for the current scene.
	 */
	public static String getCurrentScenePathLink3() {
		String[] curPaths = currentScene.getPathLinks();
		String ans = curPaths[2];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path4's linked SID for the current scene.
	 */
	public static String getCurrentScenePathLink4() {
		String[] curPaths = currentScene.getPathLinks();
		String ans = curPaths[3];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path1's button description for the current scene.
	 */
	public static String getCurrentScenePathDescription1() {
		String[] curPathDescriptions = currentScene.getPathDescriptions();
		String ans = curPathDescriptions[0];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path2's button description for the current scene.
	 */
	public static String getCurrentScenePathDescription2() {
		String[] curPathDescriptions = currentScene.getPathDescriptions();
		String ans = curPathDescriptions[1];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path3's button description for the current scene.
	 */
	public static String getCurrentScenePathDescription3() {
		String[] curPathDescriptions = currentScene.getPathDescriptions();
		String ans = curPathDescriptions[2];
		return (ans != null) ? ans : "void";
	}

	/**
	 * Retrieves Path4's button description for the current scene.
	 */
	public static String getCurrentScenePathDescription4() {
		String[] curPathDescriptions = currentScene.getPathDescriptions();
		String ans = curPathDescriptions[3];
		return (ans != null) ? ans : "void";
	}

}// end of class