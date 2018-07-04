package papacapstone.EncryptionToolsTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import papacapstone.EncryptionTools.MD5Generator;

/**
 * 
 * Tests the following class: papacapstone.EncryptionTools.MD5Generator
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
public class MD5GeneratorTest {
	private String shouldBeSameChkSum1;
	private String shouldBeSameChkSum2;
	private String shouldBeSameChkSum3;
	private String shouldBeDifferentChkSum;
	private File original;
	private File copyOfOriginal;
	private File originalAgain;
	private File originalWithChanges;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Tests that checksum is equal for the same file, even if the file is renamed
	 * or referenced as seperate objects
	 */
	@Test
	public void testChecksumEqualWhenUsingSameFile() throws IOException {
		// Try to get a checksum from an original file
		original = new File("C://Users//Nhia//Desktop//StoryFile.xml");
		shouldBeSameChkSum1 = MD5Generator.getChecksum(original);

		// Pass in the original file, but rename it and get the copy's checksum.
		// The contents are the same, so the checksum should be the same.
		copyOfOriginal = new File("C://Users//Nhia//Desktop//SameFile.xml");
		shouldBeSameChkSum2 = MD5Generator.getChecksum(copyOfOriginal);

		// Test that re-naming the same file still results in equivalent checksums
		assertEquals(shouldBeSameChkSum1, shouldBeSameChkSum2);

		// How about we try to get the checksum again of the original but with a
		// different variable
		originalAgain = new File("C://Users//Nhia//Desktop//StoryFile.xml");
		shouldBeSameChkSum3 = MD5Generator.getChecksum(originalAgain);

		// Test that two variables with the same file results in equal checksums
		assertEquals(shouldBeSameChkSum1, shouldBeSameChkSum3);
	}// end of test

	/**
	 * Tests that two different files will produce two different checksums
	 */
	@Test
	public void testChecksumDifferentWhenUsingTwoDifferentFiles() throws IOException {
		// Take the original file and modify it, then get the checksum
		originalWithChanges = new File("C://Users//Nhia//Desktop//StoryFile2.xml");
		shouldBeDifferentChkSum = MD5Generator.getChecksum(originalWithChanges);

		// Test that checksum for the original file and the altered file are different
		Assert.assertNotEquals(shouldBeSameChkSum1, shouldBeDifferentChkSum);
	}// end of test

	/**
	 * Tests that trying to find the checksum for a null value throws an error
	 */
	@Test
	public void testExceptionThrownWhenPassingInNullValue() throws IOException {
		exception.expect(NullPointerException.class);

		shouldBeDifferentChkSum = MD5Generator.getChecksum(null);
	}

}// end of class