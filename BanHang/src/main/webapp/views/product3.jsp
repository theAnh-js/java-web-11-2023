<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item"><a href="category.html">Category</a>
					</li>
					<li class="breadcrumb-item active" aria-current="page">
						Sub-category</li>
				</ol>
			</nav>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-12 col-sm-3">
			<div class="card bg-light mb-3">
				<div class="card-header bg-primary text-white text-uppercase">
					<i class="fa fa-list"></i> Categories
				</div>
				<ul class="list-group category_block">

					<c:forEach var="item" items="${listCategory}">
						<!-- Xử lý load các category và thêm active -->
						<li
							class="list-group-item ${item.categoryID eq cate_ID ? 'active' : ''}">
							<!-- Khi được active thì đổi luôn màu chữ thành trắng cho dễ nhìn :) -->
							<a class="${item.categoryID eq cate_ID ? 'text-white' : ''}"
							href="<c:url value="/product?id=${item.categoryID}"/>">
								${item.categoryName}(${item.amountOfProduct})</a>
						</li>
					</c:forEach>

				</ul>
			</div>
			<div class="card bg-light mb-3">
				<div class="card-header bg-success text-white text-uppercase">
					Last product</div>
				<c:choose>
					<c:when test="${not empty lastProduct}">
						<div class="card-body">
							<c:url value="/image?fname=${lastProduct.imageLink}" var="img"></c:url>
							<img class="img-fluid" src="${img}" />
							<h5 class="card-title">${lastProduct.productName}</h5>
							<p class="card-text">${lastProduct.description}</p>
							<p class="bloc_left_price">${lastProduct.price}$</p>
						</div>
					</c:when>
				</c:choose>


			</div>
		</div>

		<div class="col">
			<div id="content" class="row">
				<c:forEach var="item" items="${top6NewestProduct}">
					<div class="col-12 col-md-6 col-lg-4">
						<div class="card">
							<c:url value="/image?fname=${item.imageLink}" var="imgUrl"></c:url>
							<img class="card-img-top" src="${imgUrl}" alt="Card image cap" />
							<div class="card-body">
								<h4 class="card-title">
									<a href="<c:url value="/product-detail?id=${item.productID}"/>"
										title="View Product">${item.productName}</a>
								</h4>
								<p class="card-text">${item.description}</p>
								<div class="row">
									<div class="col">
										<p class="btn btn-danger btn-block">${item.price}$</p>
									</div>
									<div class="col">
										<a href="#" class="btn btn-success btn-block">Add to cart</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
			<br>
			<div class="col-12">
				<button onclick="loadAjax()" class="btn btn-primary">Xem
					thêm</button>
			</div>
			<br>
		</div>

	</div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	
<script>
	/* $(window).scroll(function(){
		if($(window).scrollTop() + $(window).height() >= $(document).height()){
			loadAjax();
		}
	}); */

	function loadAjax() {
		var amount = document.getElementsByClassName("product").length;
		$.ajax({
			url : "/BanHang/loadAjax",
			type : "post",
			data : {
				exits : amount
			},
			success : function(html) {
				$("#content").append(html);
			}
		});
	}
</script>


