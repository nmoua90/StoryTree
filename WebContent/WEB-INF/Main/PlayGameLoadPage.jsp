<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="playGameLoadPage.js"></script>

<Title>The Adventure Is About To Begin!</Title>

<link type="text/css" rel="stylesheet" href="loadPage.css">

<div id="DefaultHeader" class="header">
	<h1>Game Loading Page</h1>
</div>


</head>

<body>
	<h1>You're about to play Game ${curGameID}!</h1>

	<div id="optionPanel">
		<FORM NAME="kickoff" ACTION="GameLoaderController" METHOD="POST">
			<button name="userChoice" value="loadGame" type="submit">Begin
				the Game!</button>
			<br>
			<br>
		</FORM>

		<FORM ACTION="GameFileDownloaderController" METHOD="POST">
			<button name="dlGame" value={curGameURL}>View/Edit Game Data</button>
			<br>
			<br>
		</FORM>

		<FORM id="rater" ACTION="GameRaterController" METHOD="POST">
			<input type="submit" value="Rate Game" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="gameScore">
				<option>10</option>
				<option>9</option>
				<option>8</option>
				<option>6</option>
				<option>5</option>
				<option>4</option>
				<option>3</option>
				<option>2</option>
				<option>1</option>
				<option>0</option>
			</select>
		</FORM>
	</div>



</body>
</html>