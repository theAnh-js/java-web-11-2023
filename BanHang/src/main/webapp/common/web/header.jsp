<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<a class="navbar-brand" href="<c:url value="/home"/>">Thời Trang
		ZenZet</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a>
			</li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Categories </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				
					<!--LOAD RA DANH SACH CATEGORY-->
					<c:forEach var="item" items="${listCategory}">
						<a
							class="dropdown-item ${item.categoryID eq cate_ID ? 'text-white active' : ''}"
							href="<c:url value="/product?id=${item.categoryID}"/>">
							${item.categoryName}(${item.amountOfProduct})</a>
					</c:forEach>

				</div></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/product"/>">Sản phẩm</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Sản
					phẩm nổi bật</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/cart"/>">Giỏ
					hàng</a></li>
			<c:choose>
				<c:when test="${not empty userFromSession}">
					<li class="nav-item">
					<a class="nav-link" href="<c:url value="logout"/>">Đăng xuất | </a></li>
					<li class="nav-item nav-link text-info">Xin chào ${userFromSession.username}</li>

				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/login"/>">Đăng nhập</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
			<!-- gọi vào servlet ->  /search-product và vào phương thức get trong -->
		<form class="form-inline my-2 my-lg-0"
				action="<c:url value="/search-product"/>" method="get">
				<div class="input-group input-group-sm">
				
				 <input type="hidden" name="page" value="1">
				 <input name="search" value="${searchVal}" type="text"
					class="form-control" aria-label="Small"
					aria-describedby="inputGroup-sizing-sm" placeholder="Search..." />
					
					<%-- <input oninput="searchByName(this)" name="search" value="${searchVal}" type="text"
					class="form-control" aria-label="Small"
					aria-describedby="inputGroup-sizing-sm" placeholder="Search..." /> --%>
					
				<div class="input-group-append">
					<button type="submit" class="btn btn-secondary btn-number">
						<i class="fa fa-search"></i>
					</button>
				</div>
         
			<a class="btn btn-success btn-sm ml-3" href="<c:url value="/cart"/>"> <i
				class="fa fa-shopping-cart"></i> Cart <span
				class="badge badge-light">${sessionScope.size}</span>
			</a>
		</div>
	</form>
	</div>
</nav>


<section class="jumbotron text-center">
	<div class="container">
		<h1 class="jumbotron-heading">E-COMMERCE BOOTSTRAP THEME</h1>
		<p class="lead text-muted mb-0">Simple theme for e-commerce buid
			with Bootstrap 4.0.0. Pages available : home, category, product, cart
			& contact</p>
	</div>
</section>

