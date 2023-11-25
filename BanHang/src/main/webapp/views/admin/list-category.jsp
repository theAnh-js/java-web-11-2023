<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"
	rel="stylesheet" type="text/css" />

<link
	href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- JS -->
<!-- JS -->
<script src="https://code.jquery.com/jquery-3.7.0.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap4.min.js"
	type="text/javascript"></script>

<table id="example" class="table table-striped table-bordered"
	style="width: 100%">
	<p><a href="<c:url value="/admin/category/add"/>">Thêm Danh Mục</a></p>
	<thead>
		<tr>
			<th>STT</th>
			<th>Ảnh đại diện</th>
			<th>Tên danh mục</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cateList}" var="cate" varStatus="STT">
			<tr class="odd gradeX">
				<td>${STT.index + 1}</td>
				<c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
				<td><img src='${imgUrl}' height="150" width="200" alt="anh quan ao"></td>
				<td>${cate.categoryName}</td>
				<td><a
					href="<c:url value="/admin/category/edit?id=${cate.categoryID}"/>"
					class="center">Sửa</a> | <a
					href="<c:url value="/admin/category/delete?id=${cate.categoryID}"/>"
					class="center">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th>STT</th>
			<th>Ảnh đại diện</th>
			<th>Tên danh mục</th>
			<th>Thao tác</th>
		</tr>
	</tfoot>
</table>
<script>
	new DataTable('#example');
</script>
