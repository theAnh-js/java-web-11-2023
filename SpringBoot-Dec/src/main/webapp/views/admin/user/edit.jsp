<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
</head>
<body>
<h1>Edit User</h1>

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
		<!-- form -->
		<h2>FORM</h2>

		<div  class="mt-5 col-10 offset-1">
			<form:form action="${pageContext.request.contextPath}/admin/users/update?id=${user.id}" method="post" modelAttribute="user">
				<div class="row col-12">
					<label>Id : </label>
					<form:input path="id" name="id" disabled="true"/>	
				</div>
				<div class="row col-12">
					<label>Name : </label>
					<form:input path="username" name="username"/>	
				</div>
				<div class="row col-12">
					<label>Email :  </label>
					<form:input path="email" name="email"/>	
				</div>
				
				<button type="submit">Save</button>
			</form:form>
		</div>

<hr>
		
	<%-- 	<form:form modelAttribute="user" method="post" action="${ pageContext.request.contextPath}/admin/users/update/1"> 
			
			<div class="form-group mt-3">
				<label for="name">Name</label>
			    <form:input path="name" name="name" class="form-control" autocomplete="off" />
			    <form:errors path="name" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="">Student Code</label>
			    <form:input path="studentCode" name="studentCode"  class="form-control" autocomplete="off" />
			    <form:errors path="studentCode" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="">Email</label>
			    <form:input path="email" name="email"  class="form-control" autocomplete="off" />
			    <form:errors path="email" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="">Password</label>
			    <form:password path="password" class="form-control" autocomplete="off" disabled="true"/>
			    <form:errors path="password" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="">Password Confirm</label>
			    <form:password path="passwordConfirm" class="form-control" autocomplete="off" disabled="true"/>
			    <form:errors path="passwordConfirm" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="">Date of Birth</label>
			    <form:input path="dob" type="date" class="form-control" autocomplete="off" />
			    <form:errors path="dob" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="">Avatar</label>
			    <form:input path="avatar"  class="form-control" autocomplete="off" />
			    <form:errors path="avatar" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="">Role</label>
				<form:select path="role" id="role" class="form-control">
					<form:option value="1">Admin</form:option>
					<form:option value="2">Member</form:option>
				</form:select>
				<form:errors path="role" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="">Gender</label>
				<form:select path="gender" id="gender" class="form-control">
					<form:option value="1">Male</form:option>
					<form:option value="2">Female</form:option>
				</form:select>
				<form:errors path="gender" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<label for="">Status</label>
				<form:select path="status" id="status" class="form-control">
					<form:option value="1">Active</form:option>
					<form:option value="0">Inactive</form:option>
				</form:select>
				<form:errors path="status" element="span" cssClass="text-danger" />
			</div>
			
			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</div>
			
		</form:form> --%>
	</div>
</body>
</html>