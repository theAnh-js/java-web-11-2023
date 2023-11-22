<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <a class="navbar-brand" href="<c:url value="/home"/>">Thời Trang Mới</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Categories
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        		<!--LOAD RA DANH SACH CATEGORY-->
         <c:forEach var="item" items="${listCategory}">
         	<a class="dropdown-item ${item.categoryID eq cate_ID ? 'text-white active' : ''}" href="<c:url value="/product?id=${item.categoryID}"/>">${item.icon} ${item.categoryName}</a>
         </c:forEach>
      
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="<c:url value="/product"/>">Sản phẩm</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Sản phẩm nổi bật</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Giỏ hàng</a>
      </li>
    </ul>
   	<form class="form-inline my-2 my-lg-0">
        <div class="input-group input-group-sm">
          <input
            type="text"
            class="form-control"
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
            placeholder="Search..."
          />
          <div class="input-group-append">
            <button type="button" class="btn btn-secondary btn-number">
              <i class="fa fa-search"></i>
            </button>
          </div>
        </div>
        <a class="btn btn-success btn-sm ml-3" href="cart.html">
          <i class="fa fa-shopping-cart"></i> Cart
          <span class="badge badge-light">3</span>
        </a>
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