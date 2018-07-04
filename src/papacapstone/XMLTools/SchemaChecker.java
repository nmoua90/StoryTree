package papacapstone.XMLTools;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;

import papacapstone.ServerTools.Credentials;

import java.io.IOException;
import java.io.File;

/**
 * Compares an XML file against an XSD (schema) file.
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
public class SchemaChecker {
	private static final File schemaFile = Credentials.getSchemafile();

	/**
	 * Validates whether an XML file is valid, by checking the file against an XSD
	 * Schema.
	 */
	public static boolean fileIsValidPerXSD(File incomingXmlFile) throws SAXException, IOException {
		try {
			Source xmlFile = new StreamSource(incomingXmlFile);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema schema = schemaFactory.newSchema(schemaFile);

			Validator validator = schema.newValidator();

			validator.validate(xmlFile);

			return true;

		} catch (SAXException e) {
			return false;
		}
	}

}// end of class