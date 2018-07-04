<!DOCTYPE html>

<html>

<head>
<title>Upload Game</title>
<link rel="stylesheet" type="text/css" href="mainStylesheet.css">
</head>

<header>
	<%@include file="NewHeader.jsp"%>
</header>

<body>

	<div>
		<br> <img src=<%= "https://i.imgur.com/RlVrGvi.jpg" %>
			alt="splashPhoto" width="650" height="400" /><br>
		<br> <input class="whiteButton" type="text"
			style="text-align: center;" size="120" class="displayBox"
			value="${successMessage}" readonly disabled><br>
		<br>
		<br>
	</div>


	<div>
		<form method="POST" action="FileUploadController"
			enctype="multipart/form-data">
			Select file to upload: <input type="file" id="file" name="file"
				onchange="ValidateSize(this)" required> <br>
			<br>
			<br> <input type="hidden" name="userUploader"
				value="${user.username}" /> <input class="blackButton" type="submit"
				id="uploadButton" value="Upload File" />
		</form>
	</div>

	<br>
	<br>

	<div class="regularFooter">
		<%@include file="Footer.jsp"%>
	</div>

	<script type="text/javascript" src="uploadGame.js"></script>
</body>

</html>