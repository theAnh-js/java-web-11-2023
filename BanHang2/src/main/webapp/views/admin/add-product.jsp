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

	<form action="add" role="form" method="post"
		enctype="multipart/form-data">
		
			<div class="form-group">
				<label>Tên sản phẩm:</label> 
				<input type="text" class="form-control" name="name"/>
			</div>
		<br>
			<div class="form-group">		
				<label>Thay ảnh sản phẩm:</label> 
				<input type="file" name="imageLink"/>
			</div>
		<br>
			<div class="form-group">
				<label>Chọn danh mục:</label> 
				<select name="category" id="category">
					<c:forEach var="cate" items="${listCate}">
						<option value="${cate.categoryID}">${cate.categoryName}</option>	
					</c:forEach>
				</select>
			</div>
		<br>
			<div class="form-group">
				<label>Giá:</label> 
				<input type="text" class="form-control" name="price"/>
			</div>
		<br>
		<div class="form-group">
				<label>Mô tả sản phẩm:</label> 
				<textarea rows="5" cols="33" name="description" ></textarea>
			</div>
		<br>
			<div class="form-group">
				<label>ID người bán:</label> 
				<input type="text" class="form-control" name="sellerID"/>
			</div>
		<br>	
			<div class="form-group">
				<label>Số lượng đã bán:</label> 
				<input type="text" class="form-control" name="amount"/>
			</div>
		<br>
		
		<button type="submit" class="btn btn-default">Thêm</button>
		<button type="reset" class="btn btn-primary">Hủy</button>
	</form>
</body>
</html>