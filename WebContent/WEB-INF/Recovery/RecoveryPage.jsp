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
	<div id="message">
		<h1>Forgot your Account Information or Can't Activate your Account?</h1>
	
		<p>${errorMessage}
		
		Enter your email, and we'll send you a verification link.
		
		You'll be able to use this link to either reset your password, or activate your account.</p>
	</div>
	
	<div id="requestEmail">
		<form action="EmailRecoveryController" method="POST">
			EMAIL: <input type="email" name="newEmail" placeholder="Email" required><br><br>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<input id="emailButn" class="whiteButton" type="submit" value="Submit">
		</form>
	</div>
	
</body>

</html>