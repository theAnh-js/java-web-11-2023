<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<head>
<style type="text/css">
.pagination {
	display: flex;
	justify-content: center;
	align-items: center;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}
</style>

</head>

<body>

	<div class="well well-small">

		<c:if test="${productsByCateIdAndPaging.size() > 0}">
			<div class="row">
				<span style="margin-left: 25px;">Danh sách sản phẩm</span> <select
					class="pull-right">
					<option>A - Z</option>
					<option>Cao - Thấp</option>
				</select>
			</div>
			<div class="row-fluid">
				<ul class="thumbnails">
					<c:forEach var="item" items="${productsByCateIdAndPaging}" varStatus="loop">
						<li class="span4">
							<div class="thumbnail">
								<a href="product_details.html" class="overlay"></a> <a
									class="zoomTool" href="product_details.html"
									title="add to cart"><span class="icon-search"></span> QUICK
									VIEW</a> <a href=""><img
									src="<c:url value='/assets/user/img/${item.img}'/>"
									alt="Ảnh sản phẩm" /></a>
								<div class="caption cntr">
									<p>Manicure & Pedicure(${item.categoryId})</p>
									<p>
										<strong><fmt:formatNumber type="number"
												groupingUsed="true" value="${item.price}" />₫</strong>
									</p>
									<h4>
										<a class="shopBtn" href="#" title="add to cart"> Add to
											cart </a>
									</h4>
									<div class="actionList">
										<a class="pull-left" href="#">Add to Wish List </a> <a
											class="pull-left" href="#"> Add to Compare </a>
									</div>
									<br class="clr" />
								</div>
							</div>
						</li>
						<c:if
							test="${loop.count % 3 == 0 || loop.count == productsByCateIdAndPaging.size()}">
				</ul>
			</div>
			<c:if test="${loop.count < productsByCateIdAndPaging.size()}">
				<div class="row-fluid">
					<ul class="thumbnails">
			</c:if>
		</c:if>

		</c:forEach>
		</c:if>

		<c:if test="${productsByCateIdAndPaging.size() == 0}">
			<h3>Quý khách xin thông cảm, danh mục này hiện hết hàng.</h3>
			<p>
				<a href="<c:url value='/'/>">Xem tiếp</a>
			</p>
		</c:if>

	</div>

	<c:if test="${productsByCateIdAndPaging.size() > 0}">
		<div class="pagination">
		
			<c:if test="${currentPage > 1}">
				<a href="<c:url value='/danh-sach-san-pham/${categoryId}/${currentPage - 1}'/>">&laquo;</a>
			</c:if>
			 	
			<c:forEach var="i" begin="1" end = "${totalPage}">	
				<c:if test="${i == currentPage}"> 
				<a class="active" href="<c:url value='/danh-sach-san-pham/${categoryId}/${i}'/>">${i}</a>
				</c:if>	
				<c:if test="${i != currentPage}"> 
				<a class="" href="<c:url value='/danh-sach-san-pham/${categoryId}/${i}'/>">${i}</a>
				</c:if>		
		
			</c:forEach>
			
			<c:if test="${currentPage < totalPage}">
				<a href="<c:url value='/danh-sach-san-pham/${categoryId}/${currentPage + 1}'/>">&raquo;</a>
			</c:if>

		</div>
	</c:if>

</body>