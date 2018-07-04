<!DOCTYPE html>

<html>

<head>
	<style>
	h1, p{
			background-color: black;
			color: white;
			border-radius: 20px;
			padding: 20px;
		}
		
		#requestEmail{
			padding-left: 23em;
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
		    left: 15%;
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
	<div>
		<p>It seems that your attempted upload already exists within our file system!</p>
		
		<p>The game in our system that matches your failed upload has the following Game ID: ${gameID}</p>
		
		<form action="UploadGame">
			<input type="submit" value="Go Back to the Upload Game Page">
		</form>
	
	</div>
</body>

</html>