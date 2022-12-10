
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Mint NFT</title>
</head>

<body style="background-color: #dcefd6">
<button onclick="history.back()">Go Back</button> 

	<center>
		<h1>Mint/Create a new NFT</h1>
	</center>
	
	
	<div align="center">
		<form action="mint" method="post">
		
			<table style="background-color: #dceeee" border="1" cellpadding="5">
				<tr>
					<th>nftName: </th>
					<td align="center" colspan="3">
						<input type="text" name="nftName" size="45"  value="name" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>URL: </th>
					<td align="center" colspan="3">
						<input type="text" name="url" size="45" value="url" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Creator: </th>
					<td align="center" colspan="3">
						<input type="text" name="creator" size="45" value="creator" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Date and time of creation: </th>
					<td align="center" colspan="3">
						<input type="text" name="mintTime" size="45" value="YYYY-MM-DD HH:MM:SS" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Price </th>
					<td align="center" colspan="3"> 
						<input type="text" name="price" size="45" value="price" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>NFT ID: </th>
					<td align="center" colspan="3">
						<input type="text" name="confirmation" size="45" value="0" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="mint" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>