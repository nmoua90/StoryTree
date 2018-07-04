<HTML>
<HEAD>
<TITLE>Play A Game</TITLE>

<link type="text/css" rel="stylesheet" href="PlayGame.css">
</HEAD>

<BODY>

	<div id="gameSpace">

		<h1>${setTitle}</h1>

		<IMG src="${setPhotoURL}" height="320" width="550" />

		<p>${setBodyDesc}</p>

		<div id="Buttons">
			<FORM NAME="form1" ACTION="GameplayController" METHOD="POST">
				<button name="userChoice" class="hidden" id="Path1"
					value="${setLink1}" type="submit">${setPath1}</button>
			</FORM>

			<FORM NAME="form2" ACTION="GameplayController" METHOD="POST">
				<button name="userChoice" class="hidden" id="Path2"
					value="${setLink2}" type="submit">${setPath2}</button>
			</FORM>

			<FORM NAME="form3" ACTION="GameplayController" METHOD="POST">
				<button name="userChoice" class="hidden" id="Path3"
					value="${setLink3}" type="submit">${setPath3}</button>
			</FORM>

			<FORM NAME="form4" ACTION="GameplayController" METHOD="POST">
				<button name="userChoice" class="hidden" id="Path4"
					value="${setLink4}" type="submit">${setPath4}</button>
			</FORM>
		</div>

	</div>

	<script type="text/javascript" src="playGameScript.js?v=1"></script>
</BODY>

</HTML>