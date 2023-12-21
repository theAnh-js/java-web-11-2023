<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>

<form:form modelAttribute="user" method="post" action="/saveUser">
    <label for="username">Username:</label>
    <form:input path="username" id="username" />

    <label for="password">Password:</label>
    <form:password path="password" id="password" />

    <label for="role">Role:</label>
    <form:select path="role.id" id="role">
        <form:options items="${roles}" itemLabel="name" itemValue="id"/>
    </form:select>
    
    

    <button type="submit">Save</button>
</form:form>

</body>
</html>
