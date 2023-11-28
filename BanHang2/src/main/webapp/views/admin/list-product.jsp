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
	<p>
		<a href="<c:url value="/admin/product/add"/>">Thêm Sản Phẩm</a>
	</p>
	<thead>
		<tr>
			<th>STT</th>
			<th>Tên sản phẩm</th>
			<th>Mô tả</th>
			<th>Giá</th>
			<th>Ảnh</th>
			<th>Danh mục</th>
			<th>ID người bán</th>
			<th>Số lượng đã bán</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listAllProduct}" var="pro" varStatus="STT">
			<tr class="odd gradeX">
				<td>${STT.index + 1}</td>
				<td>${pro.productName}</td>
				<td>${pro.description}</td>
				<td>${pro.price}$</td>
				<c:url value="/image?fname=${pro.imageLink}" var="imgUrl"></c:url>
				
				<td><img src="${imgUrl}" height="150" width="200"
					alt="anh quan ao"></td>
					
				<td>
					<c:forEach var="cate" items="${listCate}">
						<c:if test="${cate.categoryID eq pro.categoryID}">
							${cate.categoryName}
						</c:if>
					</c:forEach>
				</td>
				<td>${pro.sellerID}</td>
				<td>${pro.amount}</td>
				
				<td><a
					href="<c:url value="/admin/product/edit?id=${pro.productID}"/>"
					class="center">Sửa</a> | <a
					href="<c:url value="/admin/product/delete?id=${pro.productID}"/>"
					class="center">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th>STT</th>
			<th>Tên sản phẩm</th>
			<th>Mô tả</th>
			<th>Giá</th>
			<th>Ảnh</th>
			<th>Danh mục</th>
			<th>ID người bán</th>
			<th>Số lượng đã bán</th>
			<th>Thao tác</th>
		</tr>
	</tfoot>
</table>
<script>
	new DataTable('#example');
</script>
