<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search for an NFT</title>
</head>
<button onclick="history.back()">Go Back</button> 
<body style="background-color: #dcefd6">
	<h1>Search for an NFT</h1>
	<form action="searchNFT" method="post">
	<label for="targetName">NFT Name: </label>
	<input type="text" size=45 name="targetName">
	<input type="submit" value="search">
	</form>
</body>
</html>