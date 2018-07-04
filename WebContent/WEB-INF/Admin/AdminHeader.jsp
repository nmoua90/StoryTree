<!DOCTYPE html>

<html>
	<head>
		<title>Admin Header Base</title>
			<link rel="stylesheet" type="text/css" href="AdminHeader.css">
		
	</head>
	
	<body>
		<div class="tab">
			
				<a id="AdminPanel" href="AdminPanel"><button class="tablinks">${adminUser.username}'s Panel</button></a>
		  		<a id="RemoveUser" href="Admin_RemoveUser"><button class="tablinks">Remove User</button></a>
				<a id="RemoveGameFiles" href="Admin_DeleteGame"><button class="tablinks">Remove Game Files</button></a>
				<a class="logout" href="AdminLoginController?logout=true"><button id="Logout" class="tablinks">Log Out</button></a>		
		</div>
	</body>
	
</html>
