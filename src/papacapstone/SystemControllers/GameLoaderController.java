package papacapstone.SystemControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import jdk.nashorn.internal.runtime.ParserException;
import papacapstone.DatabaseTools.FileDBQueryManager;
import papacapstone.GameLogic.GameController;

/**
 * Controller that loads GameData prior to the actual playing a game.
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
public class GameLoaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Retrieves an XML File from the File System, and prepares a GameController
	 * object, which houses all Game Data. Initializes the "Home Scene" for the
	 * GameController object, before passing the GameController to the next Servlet
	 * Controller which will play the game.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get the current session
		HttpSession session = request.getSession();

		try {
			// Create a controller for the current game
			GameController gameController = new GameController();

			// Acquire absolute path of current game
			int currentGameID = Integer.valueOf((String) session.getAttribute("curGameID"));
			String xmlFilePathLocation = (String) session.getAttribute("curGameURL");

			// Init data for the entire game, by passing in the absolute path of the file
			gameController.initGameData(xmlFilePathLocation);

			// Put the init. data within the global hashmap of active games
			session.setAttribute("currentGameController", gameController);

			// Init the data <via requests> for the next page (first painted Scene is
			// "Home")
			initDataOnScreen(gameController, request, response);

			// Increment Game Hits Counter
			incrementGameHitCount(currentGameID);

			// Forward to the next page, PlayGame.jsp will have access to request params
			routeToNextPage(request, response, "/PlayGame");
		} catch (SQLException e) {
			e.printStackTrace(); // TODO: Real direct here...
		} catch (IOException | NumberFormatException ex) {
			routeToNextPage(request, response, "/404");
			;
		} catch (ParserConfigurationException e) {
			e.printStackTrace(); // TODO: Real direct here...
		} catch (SAXException e) {
			e.printStackTrace(); // TODO: Real direct here...
		}
	}// end of doPost()

	/**
	 * Increment's the current game's hit count
	 */
	private void incrementGameHitCount(int curGameID) throws SQLException {
		FileDBQueryManager fileDBMgr = new FileDBQueryManager();
		fileDBMgr.updatePlayHits(curGameID);
		fileDBMgr.closeConnection();
	}

	/**
	 * Initializes game data that will be displayed on the next screen. Thus, this
	 * method should be called prior to displaying a new Scene.
	 */
	private void initDataOnScreen(GameController gameController, HttpServletRequest request,
			HttpServletResponse response) {
		// Set title
		String setTitle = gameController.getCurrentTitle();
		request.setAttribute("setTitle", setTitle);

		// Set the Photo
		String setPhotoURL = gameController.getCurrentScenePhotoUrl();
		request.setAttribute("setPhotoURL", setPhotoURL);

		// Set the body of the scene
		String setBodyDesc = gameController.getCurrentSceneBodyDescription();
		request.setAttribute("setBodyDesc", setBodyDesc);

		// Set paths
		String setPath1 = gameController.getCurrentScenePathDescription1();
		String setPath2 = gameController.getCurrentScenePathDescription2();
		String setPath3 = gameController.getCurrentScenePathDescription3();
		String setPath4 = gameController.getCurrentScenePathDescription4();
		request.setAttribute("setPath1", setPath1);
		request.setAttribute("setPath2", setPath2);
		request.setAttribute("setPath3", setPath3);
		request.setAttribute("setPath4", setPath4);

		// Set links
		String setLink1 = gameController.getCurrentScenePathLink1();
		String setLink2 = gameController.getCurrentScenePathLink2();
		String setLink3 = gameController.getCurrentScenePathLink3();
		String setLink4 = gameController.getCurrentScenePathLink4();
		request.setAttribute("setLink1", setLink1);
		request.setAttribute("setLink2", setLink2);
		request.setAttribute("setLink3", setLink3);
		request.setAttribute("setLink4", setLink4);
	}

	/**
	 * Helper method that routes to the next page.
	 */
	private void routeToNextPage(HttpServletRequest request, HttpServletResponse response, String nextPage) {
		try {
			getServletContext().getRequestDispatcher(nextPage).forward(request, response);
		} catch (IOException | ServletException | NullPointerException e) {
			e.printStackTrace();
		}
	}

}// end of the class