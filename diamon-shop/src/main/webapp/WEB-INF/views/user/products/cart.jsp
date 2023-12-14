<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<body>

	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="index.html">Home</a> <span class="divider">/</span>
				</li>
				<li class="active">Check Out</li>
			</ul>
			<div class="well well-small">
				<h1>
					Check Out <small class="pull-right">Có ${sessionScope.size} sản phẩm trong giỏ hàng của bạn</small>
				</h1>
				<hr class="soften" />

				<table class="table table-bordered table-condensed">
					<thead>
						<tr>
							<th>Product</th>
							<th>Description</th>
							<th>Ref.</th>
							<th>Avail.</th>
							<th>Unit price</th>
							<th>Qty</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach var="item" items="${sessionScope.cart.list}">
						
							<tr>
							<td><img width="100" src="<c:url value='/assets/user/img/${item.product.img}'/>" alt="" /></td>
							<td> ${item.product.name}</td>
							<td>-</td>
							<td><span class="shopBtn"><span class="icon-ok"></span></span>
							</td>
							<td><fmt:formatNumber type="number" groupingUsed="true" value="${item.product.price}" />₫</td>
							<td><input class="span1" style="max-width: 34px"
								placeholder="1" id="appendedInputButtons" size="16" type="number"
								value="${item.totalQuantity}" />
								<div class="input-append">
								
									<a href="<c:url value='/gio-hang/chinh-sua-gio-hang/-1/${item.product.id}'/>" class="btn btn-mini">-</a>
									<a href="<c:url value='/gio-hang/chinh-sua-gio-hang/1/${item.product.id}'/>" class="btn btn-mini">+</a>
									
									<a href="<c:url value='/gio-hang/xoa-khoi-gio-hang/${item.product.id}'/>" class="btn btn-mini btn-danger">
										<span class="icon-remove"></span>
									</a>
									
								</div></td>
							<td><fmt:formatNumber type="number" groupingUsed="true" value="${item.totalPrice}" />₫</td>
						</tr>
							
						</c:forEach>
												
						<tr>
							<td colspan="6" class="alignR">Tổng hóa đơn:</td>
							<td class="label label-primary">
							<fmt:formatNumber type="number" groupingUsed="true" value="${sessionScope.totalPriceCart}" />₫</td>
						</tr>
					</tbody>
				</table>
				
				<br />

				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>
								<form class="form-inline">
									<label style="min-width: 159px"> VOUCHERS Code: </label> <input
										type="text" class="input-medium" placeholder="CODE" />
									<button type="submit" class="shopBtn">ADD</button>
								</form>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>ESTIMATE YOUR SHIPPING & TAXES</td>
						</tr>
						<tr>
							<td>
								<form class="form-horizontal">
									<div class="control-group">
										<label class="span2 control-label" for="inputEmail">Country</label>
										<div class="controls">
											<input type="text" placeholder="Country" />
										</div>
									</div>
									<div class="control-group">
										<label class="span2 control-label" for="inputPassword">Post
											Code/ Zipcode</label>
										<div class="controls">
											<input type="password" placeholder="Password" />
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="shopBtn">Click to check
												the price</button>
										</div>
									</div>
								</form>
							</td>
						</tr>
					</tbody>
				</table>
				<a href="products.html" class="shopBtn btn-large"><span
					class="icon-arrow-left"></span> Continue Shopping </a> <a
					href="login.html" class="shopBtn btn-large pull-right">Next
					<span class="icon-arrow-right"></span>
				</a>
			</div>
		</div>
	</div>

</body>