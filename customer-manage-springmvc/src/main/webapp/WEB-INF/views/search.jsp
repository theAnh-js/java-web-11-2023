<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body >


<div  align="center">

	<h2>Search Result</h2>
		<table style="margin-top: 16px; width:50%; text-align: center;">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Address</th>	
		</tr>	
		
		<c:forEach items="${customerList}" var="item">
		<tr>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.email}</td>
			<td>${item.address}</td>
		</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>