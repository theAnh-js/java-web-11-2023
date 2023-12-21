<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Show a user</h1>

<div class="col-10 offset-1">
		<div class="row col-12">
			<label class="col-4">Id :</label>		
			<label class="col-6">${user.id}</label>		
		</div>
		<div class="row col-12">
			<label class="col-4">Username :</label>		
			<label class="col-6">${user.username}</label>		
		</div>
		<div class="row col-12">
			<label class="col-4">Email :</label>		
			<label class="col-6">${user.email}</label>		
		</div>
</div>
<hr>

</body>
</html>