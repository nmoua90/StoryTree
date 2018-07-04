package papacapstone.XMLToolsTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import papacapstone.XMLTools.SchemaChecker;

/**
 * Tests papacapstone.XMLTools.SchemaChecker.java
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
public class SchemaCheckerTest {
	File validXMLFile = new File("C:\\Users\\Nhia\\Desktop\\XML Files\\Valid.xml");
	File invalidXMLFile = new File("C:\\Users\\Nhia\\Desktop\\XML Files\\Invalid.xml");
	File emptyXMLFile;
	File notAnXMLFile;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Test that a valid XML file passes the verification
	 */
	@Test
	public void testValidXMLFileIsVerifiedAsValid() throws IOException, SAXException {
		boolean fileIsValid = SchemaChecker.fileIsValidPerXSD(validXMLFile);
		assertEquals(fileIsValid, true);
	}

	/**
	 * Test that an invalid XML file fails the verification
	 */
	@Test
	public void testErrorenousXMLFileIsVerifiedAsValid() throws IOException, SAXException {
		exception.expect(SAXException.class);

		boolean fileIsValid = SchemaChecker.fileIsValidPerXSD(invalidXMLFile);
	}

}// end of class