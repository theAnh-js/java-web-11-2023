<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:url value="/admin/category/edit" var="edit"></c:url>
	<form action="${edit}" role="form" method="post" enctype="multipart/form-data">
		
		<input name="id" value="${category.categoryID}" type="hidden">
		
		<div class="form-group">
			<label>Tên danh sách:</label>
			<input type="text" class="form-control" name="name" value="${category.categoryName}"/>
		</div>
		
		<div class="form-group">
			<c:url value="/image?fname=${category.icon}" var="imgUrl"></c:url>
			<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
			<label>Ảnh đại diện:</label>
			<input type="file" name="icon" value="${category.icon}"/>
		</div>
		
		<button type="submit" class="btn btn-default">Cập nhật</button>
		<button type="reset" class="btn btn-primary">Hủy</button>
	</form>

</body>
</html>