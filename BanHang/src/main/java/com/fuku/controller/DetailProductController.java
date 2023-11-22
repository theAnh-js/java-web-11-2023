package com.fuku.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuku.dao.CategoryDAO;
import com.fuku.dao.ProductDAO;
import com.fuku.model.CategoryModel;
import com.fuku.model.ProductModel;

@WebServlet(urlPatterns = { "/product-detail" })
public class DetailProductController extends HttpServlet {

	private static final long serialVersionUID = 7524150164437470697L;

	private ProductDAO productDAO = new ProductDAO();
	private CategoryDAO categoryDAO = new CategoryDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		int productID = Integer.parseInt(req.getParameter("id"));
		ProductModel pro = productDAO.findProductById(productID);
		
		List<CategoryModel> listCategory = categoryDAO.findAllCategories();
		
		req.setAttribute("product", pro);
		req.setAttribute("listCategory", listCategory);

		RequestDispatcher rd = req.getRequestDispatcher("/views/product_detail.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
