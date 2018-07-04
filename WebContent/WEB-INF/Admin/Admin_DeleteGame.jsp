<html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<head>
<title>Administrative Functions</title>

<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css" href="tableColorScheme.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

</head>

<header>
	<div id="DefaultHeader" class="header">
		<h1 style="text-align: center;">Admin Only Functions</h1>
		<%@include file="AdminHeader.jsp"%>
	</div>
</header>

<body>
	<h3>Manage StoryFiles</h3>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/StoryTree?verifyServerCertificate=false&useSSL=true"
		user="${initParam.dbUser}" password="${initParam.dbPass}" />
	<sql:query dataSource="${snapshot}" var="result">
           SELECT URL, Title, Author FROM FILEDATA; 
    </sql:query>

	<div class="container">
		<table class="regularTable" id="datatable">
			<thead>
				<tr>
					<th>Game ID</th>
					<th>Title</th>
					<th>Author</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="row" items="${result.rows}">
					<tr class="options">
						<td><c:out value="${row.URL}" /></td>
						<td><c:out value="${row.Title}" /></td>
						<td><c:out value="${row.Author}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>

	<div id="displaySelectedChoices">
		<form action="AdminDeleteGameController" method="POST"
			style="text-align: center;">
			<br>
			<br> 
			<font size="+2">Game ID: </font> <input type="text" class="displayBox" name="enteredGameID" id="gameID" required>
			&nbsp &nbsp
			<font size="+2">Game Title To Delete:</font> &nbsp &nbsp <input type="text" size="65" class="displayBox" name="gameTitleToDelete" id="gameTitleToDelete">
			&nbsp &nbsp
			 <input type="submit" id="deleteGameButton" value="Delete Selected Game"/> 
			
		</form>
		<form action="AdminDeleteGamesByAuthorController" method="POST" style="text-align: center;">
		<font size="+2">Author:</font><input type="text" class="displayBox" name="author" id="author" required>
		<input type ="hidden" name="enteredGameID2">
		<input type="submit" id="deleteGamesByAuthorButton" value="Delete All Games By This Author">
		<br>
		<br>
		
		<font size="+2">${successMessage}</font> 
		<font size="+2">${errorMessage}</font>
		</form>
	</div>

	<script>
		var rIndex, table = document.getElementById("datatable");

		// display selected row data into input text
		function selectedRowToInput() {

			for (var i = 1; i < table.rows.length; i++) {
				table.rows[i].onclick = function() {
					// get the selected row index
					rIndex = this.rowIndex;
					document.getElementById("gameTitleToDelete").value = this.cells[1].innerHTML;
					document.getElementById("gameID").value = this.cells[0].innerHTML;
					document.getElementById("author").value = this.cells[2].innerHTML;

				};
			}
		}
		selectedRowToInput();
	</script>

	<!-- Prepare DataTables API Call -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#datatable').dataTable({
			//	"order" : [ [ 4, "desc" ] ]
			//order the 4th column
			});
		});
	</script>
	<script type="text/javascript">
	$(document).ready(function () {
	    $('#enteredGameID').on('change', function (e) {
	       $('#enteredGameID2').val($('#enteredGameID').val());
	    });
	});
</script>
</body>
</html>