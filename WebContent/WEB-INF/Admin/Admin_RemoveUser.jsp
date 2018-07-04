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
		<h1 style="text-align: center;">Admin Remove User</h1>
		<%@include file="AdminHeader.jsp" %>
	</div>
</header>

<body>

	<h3>Manage Accounts</h3>

	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/StoryTree?verifyServerCertificate=false&useSSL=true"
		user="${initParam.dbUser}" password="${initParam.dbPass}" />

	<sql:query dataSource="${snapshot}" var="result">
           SELECT * FROM USERS;
    </sql:query>

	<div class="container">
		<table class="regularTable" id="userdatatable">
			<thead>
				<tr>
					<th>Username</th>
					<th>Email</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach var="row" items="${result.rows}">
					<tr class="options">
						<td><c:out value="${row.username}" /></td>
						<td><c:out value="${row.emailAddress}" /></td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>

	

	<div id="displaySelectedChoices">
		<form action="AdminRemoveUserController" method="POST"
			style="text-align: center;">
			<br>
			<br> 
			<font size="+2">User To Remove:</font> &nbsp &nbsp <input type="text" size="65" class="displayBox" name="enteredUserName" id="userName"> &nbsp &nbsp &nbsp &nbsp 
				<font size="+2">User Email Address:</font> <input type="text" class="displayBox" name="enteredUserEmail"
				id="userEmail"> <br>
			<br>
			<br> <input type="submit" id="removeUser" value="Remove User">
			<br>
			<br> 
			<font size="+2">${successMessage}</font>
			<font size="+2">${errorMessage}</font>
			
		</form>
	</div>

	<script>
        	var rIndex, table = document.getElementById("userdatatable");
        
        	// display selected row data into input text
            function selectedRowToInput()
            {
                
                for(var i = 1; i < table.rows.length; i++)
                {
                    table.rows[i].onclick = function()
                    {
                      // get the seected row index
                      rIndex = this.rowIndex;
                      document.getElementById("userName").value = this.cells[0].innerHTML;
                      document.getElementById("userEmail").value = this.cells[1].innerHTML;
                    
                    };
                }
            }
            selectedRowToInput();
        </script>

	<!-- Prepare DataTables API Call -->
	<script type="text/javascript">
			$(document).ready( function () {
				$('#userdatatable').dataTable();
			});
		
			</script>
			
</body>
</html>