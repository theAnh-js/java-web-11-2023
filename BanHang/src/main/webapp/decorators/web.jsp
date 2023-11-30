<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="vn">
<head>
<!-- Site meta -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Free Bootstrap 4 Ecommerce Template</title>
<!-- CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/template/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/template/css/styleCart.css"/>" rel="stylesheet"
	type="text/css" />

</head>
<body>

	<!-- header -->
	<%@include file="/common/web/header.jsp"%>
	<!-- end header -->

	<!-- content -->
	
	<dec:body/>

	<!-- end content -->

	<!-- Footer -->
	<%@include file="/common/web/footer.jsp"%>
	<!-- end footer -->


	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		type="text/javascript"></script>
		
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"
		type="text/javascript"></script>
		
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		type="text/javascript"></script>
</body>
</html>
