<!DOCTYPE html>
<html>

	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="Register.css">
	</head>
	
	<header>
	</header>
	
	<body>
		<div>
			<form id="loginForm" action="AdminLoginController" method="POST">
				AdminID: <input type="text" name=adminUsername required/><br/>
				Password: <input type="password" name=adminPassword required /><br/>
				
				${errorMessageAD}<br/><br/>
				
				<input type="submit" value="Log In">
			</form>
		</div>
	</body>

</html>