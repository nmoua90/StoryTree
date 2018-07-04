<!DOCTYPE html>

<html>
<head>
<title>HeaderBase</title>
<link rel="stylesheet" type="text/css" href="HeaderBase.css">
<script src="HeaderBase.js"></script>
</head>

<body>
	<div class="tab">
		<a id="Home" href="Home">
			<button class="tablinks">Home</button>
		</a> <a id="UserPanel" href="UserPanel">
			<button class="tablinks">${user.username}'sPanel</button>
		</a> <a id="GameLibrary" href="GameLibrary">
			<button class="tablinks">Game Library</button>
		</a> <a id="NhiaEditor" href="NhiaEditor" target="_blank">
			<button class="tablinks">Game Editor</button>
		</a> <a id="UploadGame" href="UploadGame">
			<button class="tablinks">Upload Game</button>
		</a> <a id="SelectAGame" href="SelectAGame">
			<button class="tablinks">Select A Game</button>
		</a> <a id="Tutorial" href="Tutorials">
			<button class="tablinks">Tutorials</button>
		</a> <a id="AboutU" href="AboutUs">
			<button class="tablinks">About Us</button>
		</a> <a class="logout" href="UserLoginController?logout=true">
			<button id="Logout" class="tablinks">Log Out</button>
		</a>
	</div>
</body>

</html>
