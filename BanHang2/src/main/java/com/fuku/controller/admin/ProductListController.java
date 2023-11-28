package com.fuku.controller.admin;

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

@WebServlet(urlPatterns = {"/admin/product/list"})
public class ProductListController extends HttpServlet {

	private static final long serialVersionUID = 775139699006726254L;
	
	private ProductDAO productDAO = new ProductDAO();
	private CategoryDAO cateDAO = new CategoryDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ProductModel> list = productDAO.findAllProduct();		
		List<CategoryModel> listCate = cateDAO.findAllCategories();
		
		req.setAttribute("listCate", listCate);
		req.setAttribute("listAllProduct", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/list-product.jsp");
		rd.forward(req, resp);
	}

}
