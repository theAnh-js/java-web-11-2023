<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
</head>
<body>
<h1>Create User</h1>

	<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
	  <a class="navbar-brand" href="#">Navbar</a>
	
	  <div class="collapse navbar-collapse">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Users</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Categories</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Products</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	
	<div class="mt-5 col-10 offset-1">
		<form:form action="${pageContext.request.contextPath}/admin/users/store" method="post" modelAttribute="user">
			<div class="row col-12">
				<label>Name : </label>
				<form:input path="username" />	
			</div>
			<div class="row col-12">
				<label>Email :  </label>
				<form:input path="email"/>	
			</div>
			<div class="row col-12">
				<label>Password :  </label>
				<form:input path="password"/>	
			</div>
			<button type="submit">Create</button>
			
		</form:form>
	</div>
		
<hr>

	<%-- <div class="mt-5 col-10 offset-1">
		<form
			method="POST"
			action="${ pageContext.request.contextPath }/admin/users/store">

			<div class="form-group">
				<label for="name">Name</label>
			    <input type="text" class="form-control" id="name" name="name" autocomplete="off">
			    <small id="name_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="email">Email</label>
			    <input type="email" class="form-control" id="email" name="email" autocomplete="off">
			    <small id="email_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="password">Password</label>
			    <input type="password" class="form-control" id="password" name="password" autocomplete="off">
			    <small id="password_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="password_confirm">Password Confirm</label>
			    <input type="password" class="form-control" id="password_confirm" name="password_confirm" autocomplete="off">
			    <small id="password_confirm_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="dob">Date of Birth</label>
			    <input type="text" class="form-control" id="dob" name="dob" autocomplete="off">
			    <small id="dob_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="role">Role</label>
				<select name="role" id="role" class="form-control" required>
					<option selected disabled>Choose</option>
					<option value="1">User</option>
					<option value="2">Admin</option>
				</select>
			    <small id="role_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="avatar">Image</label>
			    <input type="file" class="form-control" id="avatar" name="avatar">
			    <small id="avatar_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="gender">Gender</label>
				<select name="gender" id="gender" class="form-control" required>
					<option selected disabled>Choose</option>
					<option value="1">Male</option>
					<option value="2">Female</option>
				</select>
			    <small id="gender_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<label for="status">Status</label>
				<select name="status" id="status" class="form-control" required>
					<option selected disabled>Choose</option>
					<option value="1">Active</option>
					<option value="2">Inactive</option>
				</select>
			    <small id="status_error" class="form-text text-danger"></small>
			</div>
			<div class="form-group">
				<button class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</div>
		</form>
	</div> --%>
</body>
</html>