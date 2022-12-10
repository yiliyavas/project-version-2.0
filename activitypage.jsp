<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<style>

		body {
		background-image: url('https://www.pixelstalk.net/wp-content/uploads/2016/08/Digital-Images-For-Desktop.jpg');
		}
		
		a {
		color: black;
		background: #defeae
		}
        .container {
        	background:	#e1dfe5;
            height: 200px;
            width: 500px;
            border: 2px black;
            margin-top: 30px;
            }
          h2 {
          color: white;
          }
        h1 {
        color: white;
        }
</style>

<a class = container href="login.jsp"target ="_self" > logout</a><br><br> 

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body >
	 <center>
	 	
		<a class = container href="nftList.jsp"target="_self">ALL NFTs</a><br><br>
		 <h2>NFT Options: </h2>
		 <a class = container href="mint.jsp"target="_self">Create NFT</a> 
		 
		 <form action="createListing" method=get>
			<button type="submit">Create a Listing</button>
		</form>
		
		<form action="creatorList" method="post">
			<button type="submit">ListNftsCreated</button>
		</form>
		
		 <a href="checkListing.jsp"target="_self">Check NFT that are being Listed</a>
		 
		<form action="transfer" method=get>
			<button type="submit">Transfer an NFT</button>
		</form>

		 <h2> Search Options: </h2>
		 
		 <button onClick="window.location.href='searchNFT.jsp';">
			Search for an NFT
		</button>
		
		 <a href="userSearch.jsp"target="_self">search for User</a><br><br>
		 <!--  <a href="UserList.jsp"target="_self"> root </a>
		 <a href="User.jsp"target="_self"> root11 </a> -->s
		
		
		
		<h2>Check History</h2>
		<form action="gotBought" method=get>
			<button type="submit">Purchase History</button>
		</form>
		 
		 <form action="gotSold" method=get>
			<button type="submit">Selling History</button>
		</form>
		 
		 </center>
	</body>
</html>