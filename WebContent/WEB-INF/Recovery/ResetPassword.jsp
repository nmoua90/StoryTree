<!DOCTYPE html>

<html>

<head>
	<link rel="stylesheet" type="text/css" href="mainStylesheet.css">
	
	<style>
		h1, p{
			background-color: black;
			color: white;
			border-radius: 20px;
			padding: 20px;
		}
		
		#requestEmail{
			padding-left: 23em;
		}
		
		body{
			background-color: black;
			color: white;
	        background: url("https://files.vladstudio.com/joy/aflyingtree/wall/vladstudio_aflyingtree_1024x768_signed.jpg") no-repeat center center fixed;
	        -webkit-background-size: cover;
	        -moz-background-size: cover;
	        -o-background-size: cover;
	        background-size: cover;
			position: absolute;
		    top: 20%;
		    left: 25%;
		}
		
		.whiteButton, input{
			background-color: white;
			color: black;
			border-radius: 20px;
			border-color: black;
			font-family: Georgia;
			padding: 10px;
		}
	</style>
</head>

<body>
	<div>
		<h1>Enter a new password, and confirm the changes.</h1>
		<p>Username: ${userName} ${user.username} </p>
	
		<form action="PasswordResetController" method="POST">
			<input type="password" name="newPassword" placeholder="New Password" required><br><br>
			<input type="password" name="newPasswordCheck" placeholder="Re-enter Password" required><br><br>
			
			<input type="hidden" name="userName" value="${userName}">
			<input type="hidden" name="userNameActive" value="<%= request.getParameter("uID") %>">
			<input type="submit" value="Reset Password">
		</form>
	</div>

</body>


</html>