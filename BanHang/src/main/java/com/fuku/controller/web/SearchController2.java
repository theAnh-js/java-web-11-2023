package com.fuku.controller.web;

import java.io.IOException;
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

@WebServlet(urlPatterns = { "/search-product2" })
public class SearchController2 extends HttpServlet {

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
		
		if(listProduct != null) {
			for (ProductModel pro : listProduct) {
				System.out.println(pro.getProductName());
			}
		}else {
			System.out.println("listProduct is null now");
		}


		req.setAttribute("listCategory", listCategory);
		req.setAttribute("listAllProduct", listAllPro);
		req.setAttribute("lastProduct", lastPro);
		req.setAttribute("cate_ID", categoryIDStr);
		
		req.setAttribute("searchProduct", listProduct);
		req.setAttribute("searchVal", searchValue); // cái này để ta nhận vào value trong input search ở header, nhằm: sau khi
		// bấm search thì chuỗi vẫn được giữ nguyên trên input, không bị mất đi.
		
		// lấy user ra từ session được tạo bên LoginController
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		req.setAttribute("userFromSession", user);
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/product.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
