<html>

<head>
<title>Welcome to StoryTree!</title>
<link rel="stylesheet" href="mainStylesheet.css">


<style>
h1 {
	background-color: black;
	color: white;
	border-radius: 20px;
	padding: 20px;
}

body {
	font-family: Georgia;
	background-color: black;
	color: white;
	background:
		url("https://files.vladstudio.com/joy/aflyingtree/wall/vladstudio_aflyingtree_1024x768_signed.jpg")
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	position: absolute;
	top: 20%;
	left: 40%;
}

.whiteButton {
	background-color: white;
	color: black;
	border-radius: 20px;
	border-color: black;
	font-family: Georgia;
	padding: 10px;
}
</style>
</head>

<header> </header>

<body>

	<form id="loginForm" action="UserLoginController" method="POST">
		<h1>USER LOGIN</h1>
		<input class="whiteButton" type="text" name="username"
			placeholder="Username" required><br> <input
			class="whiteButton" type="password" name="password"
			placeholder="Password" required><br> <input
			class="whiteButton" type="submit" value="Login"><br>
	</form>

	<form action="Register" id="a">
		<input class="whiteButton" type="submit" value="Create New User"><br>
	</form>

	<form action="RecoveryPage">
		<input class="whiteButton" type="submit" value="Forgot Your Password?"><br>
	</form>

	${errorMessage}

</body>

</html>