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
import com.fuku.model.CategoryModel;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {

	private static final long serialVersionUID = -8725747538924522448L;

	private CategoryDAO categoryDAO = new CategoryDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<CategoryModel> cateList = categoryDAO.findAllCategories();
		req.setAttribute("cateList", cateList);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/list-category.jsp");
		rd.forward(req, resp);

	}
}
