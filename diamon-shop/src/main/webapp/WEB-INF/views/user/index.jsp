<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var='cartURL' value='/ajax-cart/add-to-cart'/>
<title>Trang chủ</title>

<div class="row">
	<div id="sidebar" class="span3">
		<div class="well well-small">
			<ul class="nav nav-list">
			
				<c:forEach var="item" items="${categories}">
					<li><a href="<c:url value="/danh-sach-san-pham/${item.id}/1"/>"><span
						class="icon-chevron-right"></span>${item.name}</a></li>
					<li>
				</c:forEach>
			
				<li style="border: 0">&nbsp;</li>
				<li><a class="totalInCart" href="cart.html"><strong>Total
							Amount <span class="badge badge-warning pull-right"
							style="line-height: 18px;">$448.42</span>
					</strong></a></li>
			</ul>
		</div>

		<div class="well well-small alert alert-warning cntr">
			<h2>50% Discount</h2>
			<p>
				only valid for online order. <br> <br> <a
					class="defaultBtn" href="#">Click here </a>
			</p>
		</div>
		<div class="well well-small">
			<a href="#"><img
				src="<c:url value='/assets/user/img/paypal.jpg'/>"
				alt="payment method paypal"></a>
		</div>

		<a class="shopBtn btn-block" href="#">Upcoming products <br>
			<small>Click to view</small></a> <br> <br>
		<ul class="nav nav-list promowrapper">
			<li>
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span
						class="icon-search"></span> QUICK VIEW</a> <img
						src="<c:url value='/assets/user/img/bootstrap-ecommerce-templates.PNG'/>"
						alt="bootstrap ecommerce templates">
					<div class="caption">
						<h4>
							<a class="defaultBtn" href="product_details.html">VIEW</a> <span
								class="pull-right">$22.00</span>
						</h4>
					</div>
				</div>
			</li>
			<li style="border: 0">&nbsp;</li>
			<li>
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span
						class="icon-search"></span> QUICK VIEW</a> <img
						src="<c:url value='/assets/user/img/shopping-cart-template.PNG'/>"
						alt="shopping cart template">
					<div class="caption">
						<h4>
							<a class="defaultBtn" href="product_details.html">VIEW</a> <span
								class="pull-right">$22.00</span>
						</h4>
					</div>
				</div>
			</li>
			<li style="border: 0">&nbsp;</li>
			<li>
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span
						class="icon-search"></span> QUICK VIEW</a> <img
						src="<c:url value='/assets/user/img/bootstrap-template.png'/>"
						alt="bootstrap template">
					<div class="caption">
						<h4>
							<a class="defaultBtn" href="product_details.html">VIEW</a> <span
								class="pull-right">$22.00</span>
						</h4>
					</div>
				</div>
			</li>
		</ul>

	</div>
	<div class="span9">
		<div class="well np">
			<div id="myCarousel" class="carousel slide homCar">
				<div class="carousel-inner">
					<c:forEach var="item" items = "${slides}" varStatus="index">
						<c:if test="${index.first}">
							<div class="item active">
						</c:if>		
						<c:if test="${not index.first}">
							<div class="item">
						</c:if>
									<img style="width: 100%"
									src="<c:url value='/assets/user/img/slides/${item.img}'/>"
									alt="bootstrap ecommerce templates">
								<div class="carousel-caption">
									<h4>${item.caption}</h4>
									<p>
										<span>${item.content}</span>
									</p>
								</div>
						  </div>
					</c:forEach>		
				</div>
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev">&lsaquo;</a> <a class="right carousel-control"
					href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		<!--
New Products
-->
		<div class="well well-small">
			<h3>Sản phẩm mới</h3>
			<hr class="soften" />
			<div class="row-fluid">
				<div id="newProductCar" class="carousel slide">
				
				<c:if test="${newProducts.size() > 0}">
					<div class="carousel-inner">
					    <div class="item active">						
							<ul class="thumbnails">
							<c:forEach var="item" items="${newProducts}" varStatus="loop">
								<li class="span3">
									<div class="thumbnail">
										<a class="zoomTool" href="product_details.html"
											title="add to cart"><span class="icon-search"></span>
											QUICK VIEW</a> <a href="#" class="tag"></a> <a
											href="product_details.html"><img
											src="<c:url value='assets/user/img/${item.img}'/>"
											alt="bootstrap-ring"></a>
									</div>
								</li>																	
							<c:if test="${loop.count % 4 == 0 || loop.count == newProducts.size()}">
									</ul>
								</div>
								<c:if test="${loop.count < newProducts.size()}">
								<div class="item">
									<ul class="thumbnails">
								</c:if>							
						   </c:if>	
						 </c:forEach> 					
					</div>
				</c:if>
				
					<a class="left carousel-control" href="#newProductCar"
						data-slide="prev">&lsaquo;</a> <a class="right carousel-control"
						href="#newProductCar" data-slide="next">&rsaquo;</a>
				</div>
			</div>
			<div class="row-fluid">
				<ul class="thumbnails">
					<li class="span4">
						<div class="thumbnail">

							<a class="zoomTool" href="product_details.html"
								title="add to cart"><span class="icon-search"></span> QUICK
								VIEW</a> <a href="product_details.html"><img
								src="<c:url value='/assets/user/img/b.jpg'/>" alt=""></a>
							<div class="caption cntr">
								<p>Manicure & Pedicure</p>
								<p>
									<strong> $22.00</strong>
								</p>
								<h4>
									<a class="shopBtn" href="#" title="add to cart"> Add to
										cart </a>
								</h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> <a
										class="pull-left" href="#"> Add to Compare </a>
								</div>
								<br class="clr">
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<a class="zoomTool" href="product_details.html"
								title="add to cart"><span class="icon-search"></span> QUICK
								VIEW</a> <a href="product_details.html"><img
								src="<c:url value='/assets/user/img/c.jpg'/>" alt=""></a>
							<div class="caption cntr">
								<p>Manicure & Pedicure</p>
								<p>
									<strong> $22.00</strong>
								</p>
								<h4>
									<a class="shopBtn" href="#" title="add to cart"> Add to
										cart </a>
								</h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> <a
										class="pull-left" href="#"> Add to Compare </a>
								</div>
								<br class="clr">
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<a class="zoomTool" href="product_details.html"
								title="add to cart"><span class="icon-search"></span> QUICK
								VIEW</a> <a href="product_details.html"><img
								src="<c:url value='/assets/user/img/a.jpg'/>" alt=""></a>
							<div class="caption cntr">
								<p>Manicure & Pedicure</p>
								<p>
									<strong> $22.00</strong>
								</p>
								<h4>
									<a class="shopBtn" href="#" title="add to cart"> Add to
										cart </a>
								</h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> <a
										class="pull-left" href="#"> Add to Compare </a>
								</div>
								<br class="clr">
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!--
	Featured Products
	-->
		<div class="well well-small">
			<h3>
				<a class="btn btn-mini pull-right" href="products.html"
					title="View more">VIew More<span class="icon-plus"></span></a>
				Sản phẩm nổi bật
			</h3>
			<hr class="soften" />
			<div class="row-fluid">
			
				<ul class="thumbnails">
					<c:forEach var="item" items="${highlightProducts}" varStatus="loop">
						<li class="span4">
							<div class="thumbnail">
								<a class="zoomTool" href="product_details.html"
									title="add to cart"><span class="icon-search"></span> QUICK
									VIEW</a> <a href="product_details.html"><img
									src="<c:url value='/assets/user/img/${item.img}'/>" alt=""></a>
								<div class="caption">
									<h5>${item.title}</h5>
									<h4> 
										<a class="defaultBtn" href="product_details.html"
											title="Click to view"><span class="icon-zoom-in"></span></a> 
											<%-- <a>
											<form class="my-form" style="display: inline-block;" action="<c:url value='/gio-hang/them-san-pham/${item.id}' />" method="post">												
												<input  class="btn btn-success" type="submit" value="Add">
											</form>
											</a> --%>
											
											<button style="display: inline-block;" class="add-to-cart-btn" data-product-id="${item.id}">Thêm</button>
											
											
											<span class="pull-right"><fmt:formatNumber type="number" groupingUsed="true" value="${item.price}" />₫</span>
											<%-- <a
											class="shopBtn" href="<c:url value='/gio-hang/them-san-pham/${item.id}'/>" title="add to cart">
											<span
											class="icon-plus">
											</span>
											</a>  --%>
														
											
									</h4>
								</div>
							</div>
						</li>				
					<c:if test="${(loop.index+1) % 3 == 0 || (loop.index + 1) == highlightProducts.size() }">
						</ul>
						<c:if test="${(loop.index + 1)< highlightProducts.size()}">
							<ul class="thumbnails">
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<hr>
		
		<div class="well well-small">
			<a class="btn btn-mini pull-right" href="#">Xem thêm <span
				class="icon-plus"></span></a> Tất cả sản phẩm
		</div>
		
		
	</div>
</div>
<!-- 
Clients 
-->
<section class="our_client">
	<hr class="soften" />
	<h4 class="title cntr">
		<span class="text">Manufactures</span>
	</h4>
	<hr class="soften" />
	<div class="row">
		<div class="span2">
			<a href="#"><img alt="" src="assets/user/img/1.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/user/img/2.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/user/img/3.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/user/img/4.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/user/img/5.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/user/img/6.png"></a>
		</div>
	</div>
</section>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function() {
        $(".add-to-cart-btn").click(function() {
            var productId = $(this).data("product-id");

            $.ajax({
                type: "POST",
                url: "${cartURL}",
                data: { productId: productId },
                success: function(response) {
                    console.log(response);
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });
    });
</script>
