<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script
	src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>

<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
	margin-bottom: 20px;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

.container {
	padding: 16px;
}
</style>
</head>

<body>

	<!-- Mỗi khi ta chuyển sang trang khác thì ta cần gọi đến controller 
để lấy ra list các bài viết tiếp theo, cụ thể ở đây là controller admin-news.
Do đó, ta cần tạo 1 form để submit và action đến controller.
 -->
	<form action="<c:url value='/admin-news'/>" id="formSubmit" method="get">
		<div class="container">
			<h1>Danh sách bài viết</h1>
			<p>(tui add được quả Plugin phân trang vào ròi đó)</p>
			
			<h2><a title="Thêm bài viết" href='<c:url value="/admin-news?type=edit" />'>ADD</a></h2>
			<button title="Thêm bài viết" id="btnDelete" type="button">DELETE</button>
			
			<h2></h2>
			<table>
				<tr>
					<th>Tên bài viết</th>
					<th>Mô tả ngắn</th>
					<th>Thao tác</th>
				</tr>
				<!-- model ở đây là key được truyền từ controller qua,
			cụ thể ở đây là 1 model - NewsModel. Mà NewsModel lại kế thừa
			từ AbstractModel nên có thể gọi vào list để get ra list NewsModel. 
			 -->
				<c:forEach var="item" items="${model.list}">
					<tr>
						<td>${item.title}</td>
						<td>${item.shortDescription}</td>
						<td>
							<!-- thay vì phải viết href='<c:url value="/admin-news?type=edit&id=${item.id}"/>', 
							thì ta viết: -->
							<c:url var="editUrl" value="/admin-news">
								<c:param name="type" value="edit"/>
								<c:param name="id" value="${item.id}"/>
							</c:url>
							<a title="Cập nhật bài viết" 
							   href='${editUrl}'>
							   EDIT</a>
						 </td>
					</tr>
				</c:forEach>

			</table>

			<nav aria-label="Page navigation">
				<ul class="pagination" id="pagination"></ul>
				<input type="hidden" value="" id="page" name="page" /> <input
					type="hidden" value="" id="maxPageItem" name="maxPageItem" /> <input
					type="hidden" value="" id="sortName" name="sortName" /> <input
					type="hidden" value="" id="sortBy" name="sortBy" />
					<input type="hidden" value="" id="type" name="type">
			</nav>
		</div>
	</form>

	<script type="text/javascript">
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 5,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#maxPageItem').val(limit); // set limit vào input có id="maxPageItem" name="maxPageItem" 
						$('#page').val(page); //set giá trị của page hiện tại vào input có  id="page" và name="page"
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#formSubmit').submit(); // khi click thì ta gọi vào form, và dùng hàm submit() để submit		
					}
				}
			});
		});
	</script>
</body>
</html>
