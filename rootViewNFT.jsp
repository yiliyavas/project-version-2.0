<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page NFT</title>
</head>
<body>

<div align = "center">
	
	<form action = "initializeNFT">
		<input type = "submit" value = "Initialize the Database for NFT"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all NFTs</h1>
    <div align="center">
        <table style="background-color: #dceeee" border="1" cellpadding="6">
            <caption><h2>List of NFTs</h2></caption>
            <tr>
                <th>NFT ID</th>
                <th>NFT Name</th>
                <th>URL</th>
                <th>Creator</th>
                <th>Owner</th>
                <th>mint Time</th>
                <th>description</th>
                
            </tr>
            <c:forEach var="nfts" items="${listNFT}">
                <tr style="text-align:center">
                    <td><c:out value="${nfts.nftid}" /></td>
                    <td><c:out value="${nfts.nftName}" /></td>
                    <td><c:out value="${nfts.URL}" /></td>
                    <td><c:out value="${nfts.owner}" /></td>
                    <td><c:out value="${nfts.creator}" /></td>
                    <td><c:out value="${nfts.mintTime}" /></td>
                    <td><c:out value="${nfts.descr}"/></td>
             </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>