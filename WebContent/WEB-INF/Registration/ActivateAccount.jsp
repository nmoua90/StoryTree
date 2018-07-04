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
		    left: 35%;
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
		<h1>User Recovery Options Page</h1>
		<p>Choose an Option</p><br>
	</div>

	<div>
		<form action="ActivationController" method="POST">
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<input type="submit" name="activateButton" value="Activate My Account">
			<input type="hidden" name="activationValue" value= <%= request.getParameter("activateID") %> >
		</form>
	
		<form action="PasswordRecoveryController" method="POST">
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<input type="submit" name="pwdResetButton" value="Reset My Password">
			<input type="hidden" name="activationValue" value= <%= request.getParameter("activateID") %> >
		</form>
	</div>
	
		
</body>

</html>