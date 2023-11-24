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
							href="<c:url value="/product?id=${item.categoryID}"/>">${item.icon}
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
					<c:when test="${empty cate_ID && empty searchProduct}">
						<c:forEach var="cate" items="${listCategory}">
						
						<!-- Chi nhung category chua san pham moi in ra doan html nay -->
						<c:forEach var="cateIDInProductList" items="${categoryIDInListProduct}">
							<c:if test="${cateIDInProductList eq cate.categoryID}">
									<div class="container mt-3">
									<div class="row">
										<div class="col-sm">
											<div class="card">
												<div class="card-header bg-primary text-white text-uppercase">
													<i class="fa fa-star"></i> ${cate.categoryName}
												</div>
												<div class="card-body">
													<div class="row">
								</c:if>
								</c:forEach>
						
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
													
													<!-- Chi nhung category chua san pham moi in ra doan html nay -->
												<c:forEach var="cateIDInProductList" items="${categoryIDInListProduct}">
						 						<c:if test="${cateIDInProductList eq cate.categoryID}">
											</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						 </c:if>
					</c:forEach>
											
						</c:forEach>
					</c:when>

					<c:when test="${empty cate_ID && not empty searchProduct}">
						<c:forEach var="cate" items="${listCategory}">


							<c:forEach var="item" items="${searchProduct}">
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
									</c:when>
								</c:choose>
							</c:forEach>

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

		<!-- KHI bấm vào từng category thì các biến như currentPage, totalPage... không có nên 
			đoạn code phân trang dưới sẽ không chạy, không hiện trên UI.
		 -->
		<div class="col-12">
					<nav aria-label="...">
						<ul class="pagination">
							<c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link" href="<c:url value="/product?page=${currentPage-1}"/>"
								>Previous</a></li>
							</c:if>
									
							<c:forEach var="i" begin="1" end = "${totalPage}">
								<li class="page-item ${i eq currentPage ? 'active' : ''}"><a class="page-link" href="<c:url value="/product?page=${i}"/>"> ${i} </a></li>
							</c:forEach>				
							
							<c:if test="${currentPage < totalPage}">
								<li class="page-item"><a class="page-link" href="<c:url value="/product?page=${currentPage+1}"/>">Next</a></li>
							</c:if>
							
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
<</div>

