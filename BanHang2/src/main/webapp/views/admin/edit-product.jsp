<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<form action="<c:url value="/admin/product/edit"/>" role="form" method="post" enctype="multipart/form-data">

	<input name="id" value="${product.productID}" type="hidden">

	<div class="form-group">
		<label>Tên sản phẩm:</label> <input type="text" class="form-control"
			name="name" value="${product.productName}" />
	</div>
<br>
	<div class="form-group">
		<c:url value="/image?fname=${product.imageLink}" var="imgU"></c:url>
		<img class="img-responsive" width="100px" src="${imgU}" alt="ảnh sản phẩm">
<br>		
		<label>Thay ảnh sản phẩm:</label> 
		<input type="file" name="imageLink" value="${product.imageLink}" />
	</div>
<br>
	<div class="form-group">
		<label>Thuộc danh mục:</label> 
		<select name="category" id="category">
			<c:forEach var="cate" items="${listCate}">
				<c:choose>
					<c:when test="${cate.categoryID eq product.categoryID}">
						<option selected value="${cate.categoryID}">${cate.categoryName}</option>
					</c:when>
					<c:otherwise>
						<option value="${cate.categoryID}">${cate.categoryName}</option>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
		</select>
	</div>
<br>
	<div class="form-group">
		<label>Giá:</label> <input type="text" class="form-control"
			name="price" value="${product.price}" />
	</div>
<br>
	<div class="form-group">
		<label>ID người bán:</label> <input type="text" class="form-control"
			name="sellerID" value="${product.sellerID}" />
	</div>
<br>	
	<div class="form-group">
		<label>Số lượng đã bán:</label> <input type="text" class="form-control"
			name="amount" value="${product.amount}" />
	</div>
<br>
	<button type="submit" class="btn btn-default">Cập nhật</button>
	<button type="reset" class="btn btn-primary">Hủy</button>

</form>