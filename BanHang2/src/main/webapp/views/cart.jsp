<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
   
<section class="h-100 gradient-custom">
  <div class="container py-5">
    <div class="row d-flex justify-content-center my-4">
      <div class="col-md-8">
        <div class="card mb-4">
          <div class="card-header py-3">
          <c:choose>
          	<c:when test="${size eq 0}">
          		<h5 class="mb-0">Giỏ hàng của bạn chưa có sản phẩm nào.</h5>
          	</c:when>
          	<c:otherwise>
          	   <h5 class="mb-0">Giỏ hàng của bạn có ${size} loại sản phẩm.</h5>
          	</c:otherwise>
          </c:choose>
           
          </div>
          <div class="card-body">
          
          
            <!-- Single item -->
          <c:set var="o" value="${sessionScope.cart}"/>
          
          	<c:forEach items="${o.listItem}" var="item">
	            <div class="row">
	              <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
	                <!-- Image -->
	                <div class="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
	                  <img src="https://mdbcdn.b-cdn.net/img/Photos/Horizontal/E-commerce/Vertical/12a.webp"
	                    class="w-100" alt="Blue Jeans Jacket" />
	                  <a href="#!">
	                    <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
	                  </a>
	                </div>
	                <!-- Image -->
	              </div>
	
	              <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
	                <!-- Data -->
	                <p><strong>${item.product.productName}</strong></p>
	                <p>Color: blue</p>
	                <p>Size: M</p>
	                
	                <form action="<c:url value="/change"/>" method="post">
	                
	                	<input type="hidden" name="id" value="${item.product.productID}">
		                <button type="submit" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip"
			                  title="Remove item">
			                 	<i>Xóa</i>
			            </button>
	                </form>
   
	                <!-- Data -->
	              </div>
	
	              <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
	                <!-- Quantity -->
	                <div class="d-flex mb-4" style="max-width: 300px">
	                
	                  <a href="<c:url value="/change?num=-1&id=${item.product.productID}"/>" class="btn btn-primary px-3 me-2"><i class="">-</i></a>
	
	                  <div class="form-outline">
	                    <input id="form1" min="0" name="quantity" value="${item.quantity}" readonly="readonly" type="number"  class="form-control" />
	                    <label class="form-label" for="form1">Quantity</label>
	                  </div>
	
	                 <a href="<c:url value="/change?num=1&id=${item.product.productID}"/>" class="btn btn-primary px-3 me-2"><i class="">+</i></a>
	                </div>
	                <!-- Quantity -->
	
	                <!-- Price -->
	                <p class="text-start text-md-center">
	                  Đơn giá: <strong>$${item.product.price}</strong>
	                </p>
	                 <p class="text-start text-md-center">
	                  Tổng: <strong>$${item.product.price * item.quantity}</strong>
	                </p>
	                <!-- Price -->
	              </div>
	            </div>
	            <hr class="my-4" />
           	 </c:forEach>
            <!-- Single item -->
            
          </div>
        </div>
        <div class="card mb-4">
          <div class="card-body">
            <p><strong>Expected shipping delivery</strong></p>
            <p class="mb-0">12.10.2020 - 14.10.2020</p>
          </div>
        </div>
        <div class="card mb-4 mb-lg-0">
          <div class="card-body">
            <p><strong>We accept</strong></p>
            <img class="me-2" width="45px"
              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
              alt="Visa" />
            <img class="me-2" width="45px"
              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
              alt="American Express" />
            <img class="me-2" width="45px"
              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
              alt="Mastercard" />
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card mb-4">
          <div class="card-header py-3">
            <h5 class="mb-0">Summary</h5>
          </div>
          <div class="card-body">
            <ul class="list-group list-group-flush">
              <li
                class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                Tổng đơn hàng
                <span>$${totalMoney}</span>
              </li>
              <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                Phí ship
                <span>Không mất phí</span>
              </li>
              <li
                class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                <div>
                  <strong>Total amount</strong>
                  <strong>
                    <p class="mb-0">(including VAT)</p>
                  </strong>
                </div>
                <span><strong>$${totalMoney}</strong></span>
              </li>
            </ul>
			<form action="<c:url value="/checkout"/>" method="post">
				<button type="submit" class="btn btn-primary btn-lg btn-block">
	              Go to checkout
	            </button>
			</form>
	            
          </div>
        </div>
      </div>
    </div>
  </div>
</section>