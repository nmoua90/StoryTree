package papacapstone.XMLTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import papacapstone.GameLogic.Scene;

/**
 * Parses an XML file and returns a HashMap of GameData (specifically, a HashMap
 * of Scene Objects)
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
public class XMLParser {
	private Map<String, Scene> data;
	private List<String> listedTitleScenes;
	private List<String> referencedPathScenes;
	private String[] pathLinks;
	private String[] pathDescriptions;
	private String sid;
	private int numPaths;
	private String photo;
	private String sceneDesc;
	private String gameTitle;
	private int classID;

	/**
	 * Standard Constructor instantiates instance variables, and adds the "HOME"
	 * scene to the list of referenced path scenes.
	 * 
	 * Note: The addition of the "HOME" scene to the list of referenced path scenes
	 * is done in order to perserve the equality check that occurs within the
	 * scenesInValidState() method.
	 */
	public XMLParser() {
		this.data = new HashMap<String, Scene>(10, 0.75F);
		this.listedTitleScenes = new ArrayList<String>();
		this.referencedPathScenes = new ArrayList<String>();
		referencedPathScenes.add("HOME"); // added for equality check
		this.pathLinks = new String[4];
		this.pathDescriptions = new String[4];
	}

	/**
	 * Parses the XML for the following elements: {TITLE, CLASS_ID, SCENE}
	 */
	public void parseFile(File xmlFile) throws SAXException, IOException, ParserConfigurationException {

		// Obtain DocumentBuilder and pass XML to it
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

		// Obtain a TITLE element and find out its value
		NodeList titleElement = document.getElementsByTagName("TITLE");
		parseTitleContent(titleElement);

		// Obtain a CLASS_ID element and find out its value
		NodeList classIDElement = document.getElementsByTagName("CLASS_ID");
		parseClassIDContent(classIDElement);

		// Obtain every SCENE element, and all of the attributes under a scene
		NodeList elementsList = document.getElementsByTagName("SCENE");
		parseSceneAttributes(elementsList);

	}// end of parseFile

	/**
	 * Parses the XML for the following elements: {TITLE, CLASS_ID}
	 */
	public List parseFileForTitleAndClassID(File xmlFile)
			throws SAXException, IOException, ParserConfigurationException, NumberFormatException {

		// Obtain DocumentBuilder and pass XML to it
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

		// Create container to return
		List metadata = new ArrayList();

		// Obtain a TITLE element and find out its value
		NodeList titleElement = document.getElementsByTagName("TITLE");
		Node titleNode = titleElement.item(0);
		metadata.add(titleNode.getTextContent());

		// Obtain a CLASS_ID element and find out its value
		NodeList classIDElement = document.getElementsByTagName("CLASS_ID");
		Node classIDNode = classIDElement.item(0);
		String data = classIDNode.getTextContent().replaceAll("\\s++", "");
		metadata.add(Integer.valueOf(data));

		return metadata;

	}// end of getTitleAndClassIDFromXML()

	/**
	 * Helper method which parses the body of text within a Title Element
	 */
	private void parseTitleContent(NodeList list) {
		Node node = list.item(0);
		gameTitle = node.getTextContent();
	}

	/**
	 * Helper method which parses the body of text within a Class_ID Element
	 */
	private void parseClassIDContent(NodeList list) {
		Node node = list.item(0);
		String data = node.getTextContent().replaceAll("\\s++", "");
		classID = Integer.valueOf(data);
	}

	/**
	 * Helper method which parses the Attributes within a Scene Element
	 */
	private void parseSceneAttributes(NodeList elementsList) {
		// Loop through all SCENE element/attributes
		for (int i = 0; i < elementsList.getLength(); i++) {
			Node node = elementsList.item(i);
			pathLinks = new String[4];
			pathDescriptions = new String[4];

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// Populated data as needed
				Element currentElement = (Element) node;
				sid = currentElement.getAttribute("sid");
				numPaths = Integer.valueOf(currentElement.getElementsByTagName("NUM_PATHS").item(0).getTextContent());
				photo = currentElement.getElementsByTagName("PHOTO").item(0).getTextContent();
				sceneDesc = currentElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();

				// Parse Scene Desc for special values
				sceneDesc = sceneDesc.replaceAll("%p", "<br><br>");
				sceneDesc = sceneDesc.replaceAll("%i", "<i>");
				sceneDesc = sceneDesc.replaceAll("%/i", "</i>");
				sceneDesc = sceneDesc.replaceAll("%b", "<b>");
				sceneDesc = sceneDesc.replaceAll("%/b", "</b>");

				// Add current scene to temp checking queue
				listedTitleScenes.add(sid);

				// Parse path data based on number of paths
				switch (numPaths) {
				case (4):
					Element n4 = (Element) currentElement.getElementsByTagName("PATH4").item(0);
					pathLinks[3] = n4.getAttribute("link");
					pathDescriptions[3] = n4.getTextContent();
					referencedPathScenes.add(pathLinks[3]);
				case (3):
					Element n3 = (Element) currentElement.getElementsByTagName("PATH3").item(0);
					pathLinks[2] = n3.getAttribute("link");
					pathDescriptions[2] = n3.getTextContent();
					referencedPathScenes.add(pathLinks[2]);
				case (2):
					Element n2 = (Element) currentElement.getElementsByTagName("PATH2").item(0);
					pathLinks[1] = n2.getAttribute("link");
					pathDescriptions[1] = n2.getTextContent();
					referencedPathScenes.add(pathLinks[1]);
				case (1):
					Element n1 = (Element) currentElement.getElementsByTagName("PATH1").item(0);
					pathLinks[0] = n1.getAttribute("link");
					pathDescriptions[0] = n1.getTextContent();
					referencedPathScenes.add(pathLinks[0]);
				}

				// Once data instantiated after parsing, push data to HashMap
				Scene currentScene = new Scene(sid, photo, numPaths, sceneDesc, pathLinks, pathDescriptions);
				data.put(sid, currentScene);

			} // end of if

		} // end of for loop
	}// end of parseSceneElements()

	/**
	 * Verifies that all referenced path scenes, correlate to a declared Scene ID.
	 * E.G. If an SID exists within the referenced paths, it should also exist as a
	 * Scene within the HashMap.
	 */
	public boolean scenesInValidState(List<String> lScenes, List<String> rScenes) {
		if (lScenes == null && rScenes == null) {
			return true;
		}

		if ((lScenes == null && rScenes != null) || lScenes != null && rScenes == null
				|| lScenes.size() != rScenes.size()) {
			return false;
		}

		Collections.sort(lScenes);
		Collections.sort(rScenes);
		return lScenes.equals(rScenes);
	}// end of scenesInValidState()

	/**
	 * Retrieves the HashMap of GameData (a Map of Scene Objects).
	 */
	public Map<String, Scene> getData() {
		return data;
	}

	/**
	 * Retrieves the game title for a file.
	 */
	public String getGameTitle() {
		return gameTitle;
	}

	/**
	 * Retrieves the classID for a file.
	 */
	public int getClassID() {
		return classID;
	}

	/**
	 * Retrieves the listed scenes within a file.
	 */
	protected List<String> getListedScenes() {
		return listedTitleScenes;
	}

	/**
	 * Retrieves the referenced path scenes within a file.
	 */
	protected List<String> getReferencedScenes() {
		return referencedPathScenes;
	}

}// end of class