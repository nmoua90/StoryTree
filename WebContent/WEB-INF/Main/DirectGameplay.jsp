<!DOCTYPE html>
<html>
<head>
<Title>The Adventure Is About To Begin!</Title>

<link type="text/css" rel="stylesheet" href="loadPage.css">

<div id="DefaultHeader" class="header">
	<h1>Game Loading Page</h1>
</div>


</head>

<body>
	<h1>
		You're about to play Game:
		<%=request.getParameter("gameID")%>!
	</h1>

	<img
		src="https://pbs.twimg.com/profile_images/2940653109/d1ca3408b5406717a8b9faab654cfdc4.jpeg"
		width="650" height="400" />

	<form action="DirectGameplayController" method="POST">
		<input class="buttons" type="submit" name="playButton"
			value="Play This Game!"> <input type="hidden"
			name="curGameID" value=<%=request.getParameter("gameID")%>>
	</form>

</body>
</html>