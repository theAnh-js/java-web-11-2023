<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>

	<!-- HEADER-->
	<%@include file="header.jsp"%>
	<!--END HEADER-->

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
								href="<c:url value="/product?id=${item.categoryID}"/>">${item.icon}
									${item.categoryName}</a>
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
								<img class="img-fluid" src="${lastProduct.imageLink}" />
								<h5 class="card-title">${lastProduct.productName}</h5>
								<p class="card-text">${lastProduct.description}</p>
								<p class="bloc_left_price">${lastProduct.price}$</p>
							</div>
						</c:when>
					</c:choose>


				</div>
			</div>
			<div class="col">
				<div class="row">

					<!-- LOAD TAT CA SAN PHAM -->
					
					<!-- Khi cate_ID là empty thì có nghĩa là ta bấm vào mục "sản phẩm" để xem toàn bộ sản phẩm
					được phân bố theo từng category như logic dưới -->
					<!-- Còn ngược lại nếu có cate_ID thì nghĩa là ta chỉ muốn xem các product của riêng category đã click vào thôi  -->
				<c:choose>		
						<c:when test="${empty cate_ID}">
							<c:forEach var="cate" items="${listCategory}">
								<div class="container mt-3">
									<div class="row">
										<div class="col-sm">
											<div class="card">
												<div
													class="card-header bg-primary text-white text-uppercase">
													<i class="fa fa-star"></i> ${cate.categoryName}
												</div>
												<div class="card-body">
													<div class="row">

														<c:forEach var="item" items="${listAllProduct}">
															<c:choose>
																<c:when test="${cate.categoryID eq item.categoryID}">

																	<div class="col-12 col-md-6 col-lg-4">
																		<div class="card">
																			<img class="card-img-top" src="${item.imageLink}"
																				alt="Card image cap" />
																			<div class="card-body">
																				<h4 class="card-title">
																					<a
																						href="<c:url value="/product-detail?id=${item.productID}"/>"
																						title="View Product">${item.productName}</a>
																				</h4>
																				<p class="card-text">${item.description}</p>
																				<div class="row">
																					<div class="col">
																						<p class="btn btn-danger btn-block">${item.price}
																							$</p>
																					</div>
																					<div class="col">
																						<a href="#" class="btn btn-success btn-block">Add
																							to cart</a>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</c:when>
															</c:choose>
														</c:forEach>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>

						
						<c:otherwise>
							<c:forEach var="item" items="${listAllProduct}">
								<div class="col-12 col-md-6 col-lg-4">
									<div class="card">
										<img class="card-img-top" src="${item.imageLink}"
											alt="Card image cap" />
										<div class="card-body">
											<h4 class="card-title">
												<a
													href="<c:url value="/product-detail?id=${item.productID}"/>"
													title="View Product">${item.productName}</a>
											</h4>
											<p class="card-text">${item.description}</p>
											<div class="row">
												<div class="col">
													<p class="btn btn-danger btn-block">${item.price}$</p>
												</div>
												<div class="col">
													<a href="#" class="btn btn-success btn-block">Add to
														cart</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

						</c:otherwise>

				</c:choose>


					<!-- 					<div class="container mt-3">
						<div class="row">
							<div class="col-sm">
								<div class="card">
									<div class="card-header bg-primary text-white text-uppercase">
										<i class="fa fa-star"></i> Last products
									</div>
									<div class="card-body">
										<div class="row">

											load ra 4 san pham moi nhat o day


										</div>
									</div>
								</div>
							</div>
						</div>
					</div> -->

					<%--  <c:choose>
            	<c:when test="${not empty listAllProduct}">
	            	<c:forEach var="item" items="${listAllProduct}">
			         	<div class="col-12 col-md-6 col-lg-4">
			              <div class="card">
			                <img
			                  class="card-img-top"
			                  src="${item.imageLink}"
			                  alt="Card image cap"
			                />
			                <div class="card-body">
			                  <h4 class="card-title">
			                    <a href="<c:url value="/product-detail?id=${item.productID}"/>" title="View Product"
			                      >${item.productName}</a
			                    >
			                  </h4>
			                  <p class="card-text">
			                    ${item.description}
			                  </p>
			                  <div class="row">
			                    <div class="col">
			                      <p class="btn btn-danger btn-block">${item.price} $</p>
			                    </div>
			                    <div class="col">
			                      <a href="#" class="btn btn-success btn-block"
			                        >Add to cart</a
			                      >
			                    </div>
			                  </div>
			                </div>
			              </div>
			            </div>
	         		</c:forEach>	
            	</c:when>
           </c:choose>  --%>


					<div class="col-12">
						<nav aria-label="...">
							<ul class="pagination">
								<li class="page-item disabled"><a class="page-link"
									href="#" tabindex="-1">Previous</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item active"><a class="page-link" href="#">2
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">Next</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER-->
	<%@include file="footer.jsp"%>
	<!--END FOOTER-->

	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		type="text/javascript"></script>
</body>
</html>
