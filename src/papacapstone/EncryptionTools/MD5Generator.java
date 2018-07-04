package papacapstone.EncryptionTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Provides the capability to compute a checksum for an input file.
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
public class MD5Generator {

	/**
	 * Computes the checksum for a file.
	 */
	public static String getChecksum(File inputFile) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(inputFile);
			String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
			fis.close();
			return md5;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}// end of class