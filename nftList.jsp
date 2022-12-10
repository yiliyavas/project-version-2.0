<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All NFT list</title>
</head>
<body style="background-color: #dcefd6">
<button style="background-color: white" onclick="history.back()">Go Back</button> 
   <div align="center">
        <table style="background-color: #dceeee"  border="1" cellpadding="5">
            <caption><h2>List of NFT</h2></caption>
            <tr>
            	<th>NFT ID</th>
				<th>NFT name</th>
                <th>URL</th>
                <th>Creator</th>
                <th>Current Owner</th>
                <th>mint Time</th>
                <th>description</th>
                <th>price</th>
                

            </tr>
            <c:forEach var="nfts" items="${listNft}">
                <tr style="text-align:center">
                    <td><c:out value="${nfts.nftName}" /></td>
                    <td><c:out value="${nfts.url}" /></td>
                    <td><c:out value="${nfts.creator}" /></td>
                    <td><c:out value="${nfts.owner}" /></td>
                    <td><c:out value="${nfts.mintTime}" /></td>
                    <td><c:out value="${nfts.description}"/></td>
                    <td><c:out value="${nfts.price}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>