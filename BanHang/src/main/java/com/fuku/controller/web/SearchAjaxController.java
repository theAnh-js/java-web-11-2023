package com.fuku.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuku.dao.CategoryDAO;
import com.fuku.dao.ProductDAO;
import com.fuku.model.CategoryModel;
import com.fuku.model.ProductModel;
import com.fuku.model.UserModel;

@WebServlet(urlPatterns = { "/search-ajax-product" })
public class SearchAjaxController extends HttpServlet {

	private static final long serialVersionUID = -4117808038310427295L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		CategoryDAO categoryDAO = new CategoryDAO();
		ProductDAO productDAO = new ProductDAO();

		List<CategoryModel> listCategory = categoryDAO.findAllCategories();
		List<ProductModel> listAllPro = null;
		ProductModel lastPro = null;

		String categoryIDStr = req.getParameter("id");
		if (categoryIDStr == null) {
			listAllPro = productDAO.findAllProduct();
			lastPro = productDAO.findLastProduct();
		} else {
			int categoryID = Integer.parseInt(categoryIDStr);
			listAllPro = productDAO.findAllProductByCategoryId(categoryID);
			lastPro = productDAO.findLastProductInEachCategory(listAllPro);
		}
		
		String searchValue = req.getParameter("search");

		List<ProductModel> listProduct = productDAO.findAllProductByString(searchValue);
		

		req.setAttribute("listCategory", listCategory);
		req.setAttribute("listAllProduct", listAllPro);
		req.setAttribute("lastProduct", lastPro);
		req.setAttribute("cate_ID", categoryIDStr);
		
		// lấy user ra từ session được tạo bên LoginController
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		req.setAttribute("userFromSession", user);
		req.setAttribute("searchVal", searchValue); // cái này để ta nhận vào value trong input search ở header, nhằm: sau khi
		// bấm search thì chuỗi vẫn được giữ nguyên trên input, không bị mất đi.
		
		
		PrintWriter out  = resp.getWriter();
		for (ProductModel p : listProduct) {
			out.println("<div class=\"product col-12 col-md-6 col-lg-4\">\r\n"
					+ "											<div class=\"card\">\r\n"
					+ "												<img class=\"card-img-top\" src=\"/BanHang/image?fname="+ p.getImageLink()  +"\"\r\n"
					+ "													alt=\"Card image cap\" />\r\n"
					+ "												<div class=\"card-body\">\r\n"
					+ "													<h4 class=\"card-title\">\r\n"
					+ "														<a href=\"/product-detail?id="+ p.getProductID() +" title=\"View Product\">" + p.getProductName()+ "</a>\r\n"
					+ "													</h4>\r\n"
					+ "													<p class=\"card-text\">"+p.getDescription()+"</p>\r\n"
					+ "													<div class=\"row\">\r\n"
					+ "														<div class=\"col\">\r\n"
					+ "															<p class=\"btn btn-danger btn-block\">"+p.getPrice()+"\r\n"
					+ "																$</p>\r\n"
					+ "														</div>\r\n"
					+ "														<div class=\"col\">\r\n"
					+ "															<a href=\"#\" class=\"btn btn-success btn-block\">Add\r\n"
					+ "																to cart</a>\r\n"
					+ "														</div>\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "											</div>\r\n"
					+ "										</div>");
		}
	}
}
