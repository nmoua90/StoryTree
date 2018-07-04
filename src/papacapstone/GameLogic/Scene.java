package papacapstone.GameLogic;

/**
 * Contains metadata belonging to a Scene.
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
public class Scene {
	private String sid;
	private String photo;
	private int numPaths;
	private String sceneDesc;
	private String[] pathLinks = new String[4];
	private String[] pathDescriptions = new String[4];

	/**
	 * Disallow illegal Scene state to be instantiated.
	 */
	private Scene() {
	}

	/**
	 * Standard constructor sets instance variables.
	 * 
	 * @param sid
	 * @param photo
	 * @param numPaths
	 * @param sceneDesc
	 * @param pathLinks
	 * @param pathDescriptions
	 */
	public Scene(String sid, String photo, int numPaths, String sceneDesc, String[] pathLinks,
			String[] pathDescriptions) {
		this.sid = sid;
		this.photo = photo;
		this.numPaths = numPaths;
		this.sceneDesc = sceneDesc.replaceAll("\t", "");
		this.pathLinks = pathLinks;
		this.pathDescriptions = pathDescriptions;
	}

	/**
	 * Retrieves the scene id.
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * Sets the scene id.
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * Retrieves the number of paths within a scene.
	 */
	public int getNumPaths() {
		return numPaths;
	}

	/**
	 * Sets the number of paths within a scene.
	 */
	public void setNumPaths(int numPaths) {
		this.numPaths = numPaths;
	}

	/**
	 * Retrieves the photo URL within a scene.
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo URL within a scene.
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Retrieves the scene description for a scene.
	 */
	public String getSceneDesc() {
		return sceneDesc;
	}

	/**
	 * Sets the scene description for a scene.
	 */
	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	/**
	 * Retrieves an array of path links. Array contains: pathlink1's SID,
	 * pathlink2's SID, pathlink3's SID, pathlink4's SID).
	 */
	public String[] getPathLinks() {
		return pathLinks;
	}

	/**
	 * Sets an array of path links. Array contains: pathlink1's SID, pathlink2's
	 * SID, pathlink3's SID, pathlink4's SID).
	 */
	public void setPathLinks(String[] pathLinks) {
		this.pathLinks = pathLinks;
	}

	/**
	 * Retrieves an array of path descriptions. Array contains: pathlink1's button
	 * description, pathlink2's button description, pathlink3's button description,
	 * pathlink4's button description).
	 */
	public String[] getPathDescriptions() {
		return pathDescriptions;
	}

	/**
	 * Sets an array of path descriptions. Array contains: pathlink1's button
	 * description, pathlink2's button description, pathlink3's button description,
	 * pathlink4's button description).
	 */
	public void setPathDescriptions(String[] pathDescriptions) {
		this.pathDescriptions = pathDescriptions;
	}

	/**
	 * Simple toString() method which prints out values of instance variables. Used
	 * for debugging prints to console.
	 */
	public String toString() {
		return String.format(
				"SID: %s\t PATHS: %d\t PHOTO: %s\t DESC: %s\nLINK1: %s\t LINK1_DESC: %s\n"
						+ "LINK2: %s\t LINK2_DESC: %s\nLINK3: %s\t LINK3_DESC: %s\n LINK4: %s\tLINK4_DESC: %s\n",
				sid, numPaths, photo, sceneDesc, pathLinks[0], pathDescriptions[0], pathLinks[1], pathDescriptions[1],
				pathLinks[2], pathDescriptions[2], pathLinks[3], pathDescriptions[3]);
	}

}// end of Scene class