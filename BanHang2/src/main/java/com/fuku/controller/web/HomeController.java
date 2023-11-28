package com.fuku.controller.web;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet(urlPatterns = {"/home", "/trang-chu"})
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Thiet lap Tieng Viet
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		// Khoi tao DAO
		ProductDAO productDAO = new ProductDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		
		// LAY RA SAN PHAM
		// lay list danh sach sp tu productDAO
		List<ProductModel> list = productDAO.getTop4Product();
		ProductModel bestSellerPro = productDAO.getBestSellerProduct();
		List<ProductModel> listBestPro = productDAO.findTop4BestProduct();
		// Lay ra danh sach categories
		List<CategoryModel> listCategory = categoryDAO.findAllCategories();
		

		// set list danh sach sp vao req với key = list4TopProduct
		req.setAttribute("list4TopProduct", list);
		req.setAttribute("bestSellerProduct", bestSellerPro);
		req.setAttribute("listBestProduct", listBestPro);
		req.setAttribute("listCategory", listCategory);
		
		// lấy user ra từ session được tạo bên LoginController
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		req.setAttribute("userFromSession", user);
		
		// trả về trang nào?
		RequestDispatcher rq = req.getRequestDispatcher("/views/home.jsp");
		// qua đối tượng requestDispacher chuyển hướng đến trang đó bằng forward
		rq.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	
	// TEST
	public static void main(String[] args) {
		
		List<ProductModel> list = new ProductDAO().getTop4Product();
		if(list != null) {
			for (ProductModel productModel : list) {
				System.out.println(productModel.getPrice());
			}
		}else {
			System.out.println("list is null");
		}
		
	}
}
