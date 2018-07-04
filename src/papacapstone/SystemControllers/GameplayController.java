package papacapstone.SystemControllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import papacapstone.GameLogic.GameController;

/**
 * Controller which handles the dataflow of a live game.
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
public class GameplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Refreshes the current Scene on the screen based on user input
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get the current session
		HttpSession session = request.getSession();

		// Get the current game controller
		GameController gameController = (GameController) session.getAttribute("currentGameController");

		// get the user's choice
		String choice = request.getParameter("userChoice");

		// draw next scene
		prepareNextScene(gameController, request, response, choice);

		// init the data for the next page
		initDataOnScreen(gameController, request, response);

		// route to next page
		routeToNextPage(request, response, "/PlayGame");
	}// end of doPost()

	/**
	 * Prepares the next scene to be displayed, based on the user input.
	 */
	private void prepareNextScene(GameController gameController, HttpServletRequest request,
			HttpServletResponse response, String choice) {
		try {
			gameController.loadNextScene(choice);

			if (gameController.getCurrentSceneNumPaths() == 0) {
				// TODO: Should we keep track of when the game ends?
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
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

}// end of class