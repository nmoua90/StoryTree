package papacapstone.ServerTools;

/**
 * 
 * Returns the current date in yyyy-MM-dd HH:mm:ss Format
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
public class TimeKeeper {

	/**
	 * Returns the current date in yyyy-MM-dd HH:mm:ss Format (same standard as
	 * MySQL's Date Format).
	 */
	public static String getCurrentDate() {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(dt);
	}

}// end of class