<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
</head>
<body>
<h1 class="text-danger">Trang liên quan đến User do admin(${sessionScope.user.username}) quản lý</h1>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
	  <a class="navbar-brand" href="#">Navbar</a>
	
	  <div class="collapse navbar-collapse">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Users</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Categories</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Products</a>
	      </li>
	    </ul>
	  </div>
	</nav>

	<div class="col-10 offset-1 mt-5 border border-primary p-2">
	
		<form method="GET" action="${ pageContext.request.contextPath }/admin/users">
			<div class="row col-12 mt-2">
				<div class="col-6">
					<label>Sắp xếp theo</label>
					<select name="sort_by" class="form-control">
						<option value="id">Mặc định</option>
						<option value="username" <c:if test="${sortBy == 'username'}">selected</c:if> >Họ Tên</option>
						<option value="email" <c:if test="${sortBy == 'email' }">selected</c:if> > Email</option>
					</select>
				</div>
				<div class="col-6">
					<label>Thứ tự</label>
					<select name="sort_direction" class="form-control">
						<option value="asc" <c:if test="${orderBy == 'asc' }">selected</c:if>>Tăng dần</option>
						<option value="desc" <c:if test="${orderBy == 'desc' }">selected</c:if> >Giảm dần</option>
					</select>
				</div>
			</div>
			
			<div>
				<button class="btn btn-primary mt-4">Lọc</button>
				<a href="${ pageContext.request.contextPath }/admin/users"
					class="btn btn-danger mt-4" type="reset">
					Reset
				</a>
			</div>
		</form>
	</div>

	<div class="mt-5 col-10 offset-1 border border-primary p-2">
		<div class="">
			<a
				class="btn btn-success col-1"
				href="${ pageContext.request.contextPath }/admin/users/create">Create</a>
		</div>
		
		
		  <table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>Username</td>
					<td>Email</td>
					<!-- <td>Tài khoản</td>
					<td>Trạng thái</td> -->
					<td colspan="2">Thao tác</td>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${allUsers}" var="item">
			<tr>
				<td>${item.id}</td>	
				<td>${item.username}</td>	
				<td>${item.email}</td>	
				<td><a href="${pageContext.request.contextPath}/admin/users/edit?id=${item.id}">Edit</a> | 
				<a href="${pageContext.request.contextPath}/admin/users/delete/${item.id}">Delete</a></td>	
			</tr>
			</c:forEach>
			<%-- <c:forEach items="${ pageData.content }" var="user">
				<tr>
						<td>${ user.id }</td>
						<td>${ user.username }</td>
						<td>${ user.email }</td>
						<td>${ user.admin == 1 ? "Admin" : "User" }</td>
						<td>${ user.activated == 1 ? "Đang hoạt động" : "Vô hiệu hóa" }</td>
						<td>
							<a
								class="btn btn-primary"
								href="${ pageContext.request.contextPath }/admin/users/edit/1">Update</a>
						</td>
						<td>
							<form action="${ pageContext.request.contextPath }/admin/users/delete/1" method="POST">
								<button class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
			</c:forEach> --%>
			</tbody>
		</table>
		
		<div>
		
			<ul class="pagination">
			
				<c:if test="${currentPage > 1}">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/admin/users?sort_by=${sortBy}&sort_direction=${orderBy}&page=${currentPage - 1}">&lt;&lt;</a>
				</li>
				</c:if>
				
				
				<c:forEach begin="1" end="${totalPage}" varStatus="page">		
					<li class="page-item <c:if test='${page.index == currentPage}'>active</c:if>">
						<a href="${pageContext.request.contextPath}/admin/users?sort_by=${sortBy}&sort_direction=${orderBy}&page=${page.index}" class="page-link">${ page.index}</a>
					</li>
				</c:forEach>
				
				<c:if test="${currentPage < totalPage}">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/admin/users?sort_by=${sortBy}&sort_direction=${orderBy}&page=${currentPage + 1}">&gt;&gt;</a>
				</li>
				</c:if>
				
			</ul>
	
			<%-- <ul class="pagination">
				<c:forEach begin="0" end="${ pageData.totalPages - 1 }" varStatus="page">
					<li class="page-item">
						<a class="page-link">${ page.index + 1 }</a>
					</li>
				</c:forEach>
			</ul> --%>
		</div>
	</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>