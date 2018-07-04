<html>

<head>
<title>Tutorials</title>
<link rel="stylesheet" type="text/css" href="mainStylesheet.css?">
</head>

<header>
	<%@include file="NewHeader.jsp"%>
</header>

<body>

	<div id="sidebarTwo">
		<h3>Tutorial Options</h3>


		<div class="block">
			<form action="FilesystemController" method="POST"
				style="text-align: center;">
				<input type="hidden" name="enteredGameID" value="1"><input
					class="blackButton" type="submit" id="playGameButton"
					value="Play a Tutorial"
					onclick="window.open('PlayGameLoadPage','blank')" /> <input
					type="hidden" name="jsp" value="Tutorials">
			</form>


			<form action="HelperTable">
				<input class="blackButton" type="submit"
					value="Read Tutorial Documentation">
			</form>

		</div>
	</div>

	<div class="body" id="tutorialsBG">
		<div class="textWithinBody"></div>
	</div>

	<div class="absoluteFooter">
		<%@include file="Footer.jsp"%>
	</div>

</body>

</html>