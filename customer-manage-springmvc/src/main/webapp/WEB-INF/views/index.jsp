<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Manager</title>
<style type="text/css">
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>

<div align="center">
	<h1>Customer Manager</h1>
	
	<form action="search" method="get">
		<input type="text" name="keyword"/>
		<input type="submit" value="Search"/>
	</form>
	
	<h3>Add new customer<a href="new"> here</a></h3>
	<table style="margin-top: 16px; width:50%; text-align: center;">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Address</th>
			<th>Action</th>		
		</tr>	
		
		<c:forEach items="${listCustomer}" var="item">
		<tr>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.email}</td>
			<td>${item.address}</td>
			<td><a href="edit?id=${item.id}">Edit</a> | <a href="delete?id=${item.id}">Delete</a></td>	
		</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>