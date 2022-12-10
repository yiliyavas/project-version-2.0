<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search for an NFT user</title>
</head>
<button onclick="history.back()">Go Back</button> 
<body style="background-color: #dcefd6">
	<h1>Search for user</h1>
	<form style="background-color: #dceeee" action="searchUser" method="post">
	
	<!-- <label for="targetName"> User Name: </label> -->
	
	<input type="text" size=45 name="user">
	<input type="submit" value="search">
	</form>
</body>
</html>


