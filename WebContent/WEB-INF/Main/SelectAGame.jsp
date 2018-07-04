<!DOCTYPE html>

<html>
<head>
<title>Select A Game</title>
<link rel="stylesheet" type="text/css" href="mainStylesheet.css">

<%@include file="NewHeader.jsp"%>
</head>

<body>

	<img
		src="https://img00.deviantart.net/c0db/i/2010/036/5/b/super_mario_drawing_by_jojomalfoy.jpg"
		width="650" height="400" />


	<form action="FilesystemController" method="POST">
		Enter the Game ID: <input type="number" name="enteredGameID"></br>

		<input type="submit" name="submitGameID" value="Play This Game"
			onclick="window.open('PlayGameLoadPage','blank')" /> <input
			type="hidden" name="jsp" value="SelectAGame">
	</form>

</body>

</html>