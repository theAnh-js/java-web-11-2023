<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
</head>
<body>

	<!-- Có thể không cần đến sessionScope. nếu không có biến user nào trùng với session  -->
	<c:if test="${not empty sessionScope.user }">
		<p>Welcome, ${user.userName}</p>
		<p>
			<a href='<c:url value="/thoat?action=logout"/>'>Logout</a>
		</p>

		<h1>Tui là trang chủ - web tin tức đây!</h1>

		<h2>DANH MỤC</h2>
		<!-- EL loop to iterate over the list -->
		<c:forEach var="category" items="${categories}">
			<ul>
				<li>${category.name}</li>
			</ul>
		</c:forEach>
		<hr>
		<c:forEach var="element" items="${news}">
			<ul>
				<li>${element.title}</li>
			</ul>
		</c:forEach>
	</c:if>

	<!--Khi action là null  hoặc user empty sẽ vào đây  -->
	<c:if test="${empty sessionScope.user }">
	<h2>Xin lòng đăng nhập để tiếp tục</h2>
		<p>
			<a href='<c:url value="/dang-nhap?action=login"/>'>Login</a>
		</p>
	</c:if>
</body>
</html>