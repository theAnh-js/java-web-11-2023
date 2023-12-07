package com.fuku.admin.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuku.dao.CategoryDAO;
import com.fuku.model.CategoryModel;
import com.google.gson.Gson;

@WebServlet(urlPatterns = { "/api-admin-gson-category" })
public class CategoryAPIGsonController extends HttpServlet {

	private static final long serialVersionUID = 6192109630090979819L;

	CategoryDAO categoryDAO = new CategoryDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		List<CategoryModel> list = categoryDAO.findAllCategories();
		
		Gson gson = new Gson();
		String cateJson = gson.toJson(list);
		
		PrintWriter out = resp.getWriter();
		out.write(cateJson);
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		CategoryModel categoryModel = gson.fromJson(req.getReader(), CategoryModel.class);
		
		categoryDAO.insert(categoryModel);
		
		PrintWriter out = resp.getWriter();
		out.write("Đã thêm thành công");
		out.close();
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		CategoryModel categoryModel = gson.fromJson(req.getReader(), CategoryModel.class);
		
		categoryDAO.edit(categoryModel);
		
		PrintWriter out = resp.getWriter();
		out.write("Đã sửa thành công");
		out.close();
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		//Gson gson = new Gson();
		//CategoryModel categoryModel = gson.fromJson(req.getReader(), CategoryModel.class);
		
		// Thường thì chỉ các phương thức post, put mới cần body
		// còn get, delete không cần.
		// khi delete thì ta gửi thông tin của đối tượng cần xóa qua paramester trên url
		// Ví dụ: http://localhost:8080/BanHang/api-admin-gson-category?id=13 -> xóa thằng category có id=13
		
		int id = Integer.parseInt(req.getParameter("id"));
		categoryDAO.delete(id);
		
		PrintWriter out = resp.getWriter();
		out.write("Đã xóa thành công");
		out.close();
	}
}
