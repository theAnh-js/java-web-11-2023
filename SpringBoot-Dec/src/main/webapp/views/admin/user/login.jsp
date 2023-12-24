<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style type="text/css">
.container-form{
	display: flex;
	align-items: end;
	justify-content: center;
}
</style>

</head>
<body>

<div class="container-form" >
	<div>
	    <form action="${pageContext.request.contextPath}/login" method="post">
	      <div class="text-center mb-3">
	        <p>Sign in</p>
	      </div>
	
	      <!-- Email input -->
	      <div>
	        <input type="email" name="email" id="registerEmail" class="form-control" />
	        <label class="form-label" for="registerEmail">Email</label>
	      </div>
			<br>
	      <!-- Password input -->
	      <div>
	        <input type="password" name="password" id="registerPassword" class="form-control" />
	        <label class="form-label" for="registerPassword">Password</label>
	      </div>
	      <br>
	      <!-- Submit button -->
	      <button type="submit">Sign in</button>
	    </form>
    </div>
    
    <div >
	    <c:if test="${not empty sessionScope.errorLogin}">
		<p>${sessionScope.errorLogin}</p>
		</c:if>
    </div>
  </div>


</body>
</html>