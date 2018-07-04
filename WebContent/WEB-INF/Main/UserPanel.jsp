<!DOCTYPE html>

<html>

<head>
<title>${user.username}'s USER PANEL</title>
<link rel="stylesheet" type="text/css" href="mainStylesheet.css?">
<link rel="stylesheet" type="text/css" href="userPanel.css">

<style>
</style>
</head>

<header>
	<%@include file="NewHeader.jsp"%>
</header>

<body>
	<div id="sidebar">
		<h3>User Panel Options</h3>

		<div class="block">
			<form action="SecureGuestController" method="POST">
				<input type="hidden" name="username" value="${user.username}" /> <input
					type="hidden" name="routeTo" value="UserListedGames" /> <input
					class="blackButton" type="submit" value="View Your Uploaded Games">
			</form>
			<br>


			<form action="SecureGuestController" method="POST">
				<input type="hidden" name="uID" value="${user.username}" /> <input
					type="hidden" name="routeTo" value="ResetPassword" /> <input
					class="blackButton" type="submit" value="Reset Your Password">
			</form>
			<br>

			<form action="SuspendUserController" method="POST">
				<input type="hidden" name="deactiveButton" /> <input
					class="blackButton" type="submit" value="Deactivate Your Account"
					onclick="return confirm('Are you sure?');">
			</form>
			<br>

			<form action="DeleteUserController" method="POST">
				<input type="hidden" name="deleteButton" /> <input
					class="blackButton" type="submit" value="Delete Your Account"
					onclick="return confirm('Are you sure?');">
			</form>
			<br>

		</div>
	</div>

	<div class="body" id="userPanelBG">
		<div class="textWithinBody"></div>
	</div>



	<div class="absoluteFooter">
		<%@include file="Footer.jsp"%>
	</div>

	<script type="text/javascript" src="userPanel.js"></script>

</body>

</html>