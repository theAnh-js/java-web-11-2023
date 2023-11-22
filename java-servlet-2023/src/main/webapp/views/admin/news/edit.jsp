<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa bài viết</title>
</head>
<body>

<div>
<label for="categoryCode">Thể loại</label>
  <select name="categoryCode" id="categoryCode">
  	  <option value="">Chọn loại bài viết</option>
  	  <c:forEach var="item" items="${categories}">
  	  		<option value="${item.code}">${item.name}</option>
  	  </c:forEach>
  </select>
  <br><br>
</div>

<div>
<label>Tiêu đề</label>
<input type="text" id="title" name="title" value="${model.title}">
</div>

<div>
<label>Hình đại diện</label>
<input type="text" id="thumbnail" name="thumbnail" value="${model.thumbnail}">
</div>

<div>
<label>Mô tả ngắn</label>
<input type="text" id="shortDescription" name="shortDescription" value="${model.shortDescription}">
</div>

<div>
<label>Nội dung</label>
<input type="text" id="content" name="content" value="${model.content}">
</div>

</body>
</html>