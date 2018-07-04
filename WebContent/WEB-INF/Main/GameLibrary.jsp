<!DOCTYPE html>

<html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<head>
<title>Browse the Storytree Game Catalog</title>

<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css" href="mainStylesheet.css">
<link rel="stylesheet" type="text/css" href="tableColorScheme.css">
</head>

<header>
	<%@include file="NewHeader.jsp"%>
</header>

<body>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/StoryTree?verifyServerCertificate=false&useSSL=true"
		user="${initParam.dbUser}" password="${initParam.dbPass}" />

	<sql:query dataSource="${snapshot}" var="result">
	           SELECT playHits, URL, Author, Title, ClassID, uploadDate, Rating FROM FILEDATA ORDER BY RATING DESC limit 200;
	    </sql:query>

	<div class="container">
		<table class="regularTable" id="datatable">
			<thead>
				<tr>
					<th>Times Played</th>
					<th>Game ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Class ID</th>
					<th>Upload Date</th>
					<th>Rating</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="row" items="${result.rows}">
					<tr class="options">
						<td><c:out value="${row.playHits}" /></td>
						<td><c:out value="${row.URL}" /></td>
						<td><c:out value="${row.Title}" /></td>
						<td><c:out value="${row.Author}" /></td>
						<td><c:out value="${row.ClassID}" /></td>
						<td><c:out value="${row.uploadDate}" /></td>
						<td><c:out value="${row.Rating}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>

	<div id="displaySelectedChoices">
		<form action="FilesystemController" method="POST"
			style="text-align: center;">
			<br>
			<br> <font size="+2">Current Selected Game:</font> &nbsp &nbsp <input
				type="text" size="65" class="displayBox" name="gameTitle"
				id="gameTitle" readonly disabled> &nbsp &nbsp &nbsp &nbsp <font
				size="+2">Game ID: </font> <input type="text" class="displayBox"
				name="enteredGameID" id="gameID" required><br>
			<br>
			<br> <input class="blackButton" type="submit"
				id="playGameButton" value="Play Selected Game"
				onclick="window.open('PlayGameLoadPage','blank')" /> <input
				type="hidden" name="jsp" value="GameLibrary">
		</form>
	</div>

	<br>
	<br>

	<div class="regularFooter">
		<%@include file="Footer.jsp"%>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="gameLibraryScript.js"></script>

</body>

</html>