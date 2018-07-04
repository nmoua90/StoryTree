<!doctype HTML>

<html>
<head>
<script src="codemirror.js"></script>
<script src="xml.js"></script>
<link rel="stylesheet" href="codemirror.css">
<link rel="stylesheet" href="colorforth.css">
<link rel="stylesheet" href="customCodeMirrorStyles.css">

<style>
button {
	background-color: black;
	color: white;
	border-radius: 20px;
	font-family: Georgia;
	padding: 5px;
}

.blackButton {
	background-color: black;
	color: white;
	border-radius: 20px;
	font-family: Georgia;
	padding: 5px;
}
</style>
</head>

<body>
	<input type="hidden" id="dataFromFile" value="${dataFromFile}">
	<input type="hidden" id="sceneCountFromFile" value="${numScenes}">

	<h3>Nhia's Funky XML Editor</h3>

	<div id="codeeditor"></div>
	<br>

	<script src="editorPasteLogic.js"></script>

	<div style="text-align: center">
		<form method="POST" action="FileUploadController"
			enctype="multipart/form-data">
			Select file to upload: <input class="blackButton" type="file"
				id="file" name="file" onchange="ValidateSize(this)" required>

			<input type="hidden" name="userUploader" value="${user.username}" />
			<input class="blackButton" type="submit" id="uploadButton"
				value="Upload File" /><br>
			<br>
			<br>
		</form>
	</div>

	<div class="headerOptions" style="text-align: center">
		<button value="XMLButton" onclick="addToDocument(this.value)">Add
			XML Header</button>
		<button value="StoryTreeHeader" onclick="addToDocument(this.value)">Add
			Story Tree Header</button>
		<button value="Title" onclick="addToDocument(this.value)">Add
			Title</button>
		<button value="ClassID" onclick="addToDocument(this.value)">Add
			Class ID (Optional)</button>
		<br>
		<br>
	</div>

	<div class="sceneOptions" style="text-align: center">
		<button value="AddZeroScenes" onclick="addToDocument(this.value)">Add
			Scene w/ (0 Paths)</button>
		<button value="AddOneScene" onclick="addToDocument(this.value)">Add
			Scene w/ (1 Paths)</button>
		<button value="AddTwoScenes" onclick="addToDocument(this.value)">Add
			Scene w/ (2 Paths)</button>
		<button value="AddThreeScenes" onclick="addToDocument(this.value)">Add
			Scene w/ (3 Paths)</button>
		<button value="AddFourScenes" onclick="addToDocument(this.value)">Add
			Scene w/ (4 Paths)</button>
		<br>
		<br>
	</div>

	<div class="saveOptions" style="text-align: center">
		<button value="" onclick="clearScreen()">Clear Screen</button>
		<button value="" onclick="saveFile('YourFile.xml', 'text/plain')">Save
			File</button>
		<br>
		<br>
	</div>

	<script type="text/javascript" src="uploadGame.js"></script>
</body>

</html>