package papacapstone.DatabaseTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import papacapstone.ServerTools.TimeKeeper;

/**
 * 
 * Contains stored procedures (SQL queries) which interact with the "FileData"
 * Table (which is stored within a SQL DB).
 * 
 * Child of Abstract class: DatabaseController.
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
public class FileDBQueryManager extends DatabaseController {
	private PreparedStatement newestGameIdStatement;
	private PreparedStatement addNewGameStatement;
	private PreparedStatement registerDuplicateCheckStatement;
	private PreparedStatement returnGameIDBasedOnChecksum;
	private PreparedStatement getCurrentRatingsHit;
	private PreparedStatement getCurrentAggregateScore;
	private PreparedStatement updateGameScore;
	private PreparedStatement updateRatingHits;
	private PreparedStatement updatePlayedHits;
	private PreparedStatement updateRatingAggregateScore;

	/**
	 * Constructor calls helper method, which connects to DB and prepares stored
	 * procedures
	 */
	public FileDBQueryManager() {
		connectToDBAndPrepareStatements();
	}

	/**
	 * Helper method, which connects to the DB and prepares all stored procedures
	 */
	public void connectToDBAndPrepareStatements() {
		try {
			if (!(connectionExists())) {
				connectToDB();
			}
			initPreparedStatements();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prepares all stored procedures
	 */
	@Override
	protected void initPreparedStatements() throws SQLException {
		// Check for duplicates query
		registerDuplicateCheckStatement = conn.prepareStatement("SELECT * " + "FROM filedata WHERE checksum = ?");

		// Newest Game URL Query
		newestGameIdStatement = conn.prepareStatement("SELECT MAX(url) " + "FROM filedata");

		// Add new game query
		addNewGameStatement = conn.prepareStatement("INSERT into FileData( url, author, title, classID, "
				+ "checksum, uploadDate, rating ) " + "VALUES(?, ?, ?, ?, ?, ?, ?)");

		// Get Game URL based on Checksum value
		returnGameIDBasedOnChecksum = conn.prepareStatement("SELECT url " + "FROM filedata " + "WHERE checksum = ?");

		// Get current number of rating hits for some game
		getCurrentRatingsHit = conn.prepareStatement("SELECT ratingHits " + "FROM filedata " + "WHERE url = ?");

		// Get current number of rating hits for some game
		getCurrentAggregateScore = conn
				.prepareStatement("SELECT ratingAggregateScore " + "FROM filedata " + "WHERE url = ?");

		// Update Gamescore of some Game
		updateGameScore = conn.prepareStatement("UPDATE filedata " + "SET rating = ? " + "WHERE url = ?");

		// Increment Number of Ratings for some Game
		updateRatingHits = conn
				.prepareStatement("UPDATE filedata " + "SET ratingHits = ratingHits + 1 " + "WHERE url = ?");

		// Increment Number of Ratings for some Game
		updateRatingAggregateScore = conn.prepareStatement(
				"UPDATE filedata " + "SET ratingAggregateScore = ratingAggregateScore + ? " + "WHERE url = ?");

		// Update Number of Plays for some Game
		updatePlayedHits = conn.prepareStatement("UPDATE filedata " + "SET playHits = playHits + 1 " + "WHERE url = ?");

	}// end of initPreparedStatements

	/**
	 * Update the game score of some game
	 */
	public void updateGameScore(int gameID, int gameScore) throws SQLException {
		// Increment Rating Hits & Aggregate Score
		updateRateHits(gameID);
		updateAgrScore(gameID, gameScore);

		// Get relevant data
		double curAggregateScore = getCurrentAggregateScore(gameID);
		double curAmtOfRatingHits = getCurrentRatingsHit(gameID);

		// Calculate new rating
		double newRating = curAggregateScore / curAmtOfRatingHits;

		updateGameScore.setDouble(1, newRating);
		updateGameScore.setDouble(2, gameID);
		updateGameScore.executeUpdate();
	}

	/**
	 * Update the rate hit counter of some game
	 */
	private void updateRateHits(int gameID) throws SQLException {
		updateRatingHits.setInt(1, gameID);
		updateRatingHits.executeUpdate();
	}

	/**
	 * Update the aggregate rating score of some game
	 */
	private void updateAgrScore(int gameID, int gameScore) throws SQLException {
		updateRatingAggregateScore.setInt(1, gameScore);
		updateRatingAggregateScore.setInt(2, gameID);
		updateRatingAggregateScore.executeUpdate();
	}

	/**
	 * Update the play hits counter of some game
	 */
	public void updatePlayHits(int gameID) throws SQLException {
		updatePlayedHits.setInt(1, gameID);
		updatePlayedHits.executeUpdate();
	}

	/**
	 * Retrieve the number of ratings for some game
	 */
	private int getCurrentRatingsHit(int gameID) throws SQLException {
		getCurrentRatingsHit.setInt(1, gameID);

		ResultSet rs = getCurrentRatingsHit.executeQuery();
		rs.next();

		return Integer.valueOf(rs.getString("ratingHits"));
	}

	/**
	 * Retrieve the current aggregate score for some game
	 */
	private int getCurrentAggregateScore(int gameID) throws SQLException {
		getCurrentAggregateScore.setInt(1, gameID);

		ResultSet rs = getCurrentAggregateScore.executeQuery();
		rs.next();

		return Integer.valueOf(rs.getString("ratingAggregateScore"));
	}

	/**
	 * Retrieves a GameID based on a checksum value within the Database.
	 */
	public String getIDWithChecksum(String chksum) throws SQLException {
		returnGameIDBasedOnChecksum.setString(1, chksum);

		ResultSet rs = returnGameIDBasedOnChecksum.executeQuery();
		rs.next();

		return rs.getString("url");
	}

	/**
	 * Retrieves the last GameID added to the database.
	 */
	public int retrieveLastAddedGameID() throws SQLException {
		ResultSet rs = newestGameIdStatement.executeQuery();
		if (rs == null) {
			return 0;
		} else {
			rs.next();
			return rs.getInt(1);
		}
	}

	/**
	 * Checks if the file being uploaded has already been uploaded to the database
	 * (uses a Checksum).
	 */
	public boolean fileIsDuplicate(String checksum) throws SQLException {
		registerDuplicateCheckStatement.setString(1, checksum);
		ResultSet rs = registerDuplicateCheckStatement.executeQuery();

		boolean fileAlreadyExists = rs.first();
		return fileAlreadyExists;
	}

	/**
	 * Adds a new game to database.
	 */
	public void addNewGame(FileMetadata storyFileObject) throws SQLException {
		addNewGameStatement.setInt(1, storyFileObject.getGameID());
		addNewGameStatement.setString(2, storyFileObject.getAuthor());
		addNewGameStatement.setString(3, storyFileObject.getTitle());
		addNewGameStatement.setInt(4, storyFileObject.getClassID());
		addNewGameStatement.setString(5, storyFileObject.getChecksum());
		addNewGameStatement.setObject(6, TimeKeeper.getCurrentDate());
		addNewGameStatement.setDouble(7, 0.0);
		addNewGameStatement.executeUpdate();
	}

}// end of class