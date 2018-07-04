<html>

<head>
	<title>Register a New User!</title>
	
	<link rel="stylesheet" type="text/css" href="mainStylesheet.css">
	
	<style>
		h1, p{
			background-color: black;
			color: white;
			border-radius: 20px;
			padding: 20px;
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
		    left: 38%;
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
	<div id="topForm">
		<h1>Register a New User!</h1>
	</div>
	
	
	<form id="createUserForm"  action="RegisterUserController" method="POST">
		<input type="text" name="newUserName" placeholder="Username" required><br>
		<input type="password" name="newPassword" placeholder="Password" required><br>
		<input type="email" name="newEmail" placeholder="Email" required><br>
		<input class="whiteButton" type="submit" value="Register"><br>
		${errorMessage}<br>
		${loginMessage}
	</form>
	
	<form action="UserLogin">
		<input class="whiteButton" type="submit" value="Go Back">
	</form>
</body>

</html>