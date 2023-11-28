package com.fuku.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fuku.dao.CategoryDAO;
import com.fuku.model.CategoryModel;
import com.fuku.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {

	private static final long serialVersionUID = 3040514203852614508L;

	CategoryDAO categoryDAO = new CategoryDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id != null) {
			CategoryModel category = categoryDAO.get(Integer.parseInt(id));
			req.setAttribute("category", category);

		}

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/edit-category.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CategoryModel category = new CategoryModel();

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("id")) {
					category.setCategoryID(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("name")) {
					category.setCategoryName(item.getString("UTF-8"));
				} else if (item.getFieldName().equals("icon")) {
					if (item.getSize() > 0) {
						String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);

						String fileName = System.currentTimeMillis() + "." + ext;

						File file = new File(Constant.DIR + "/category/" + fileName);

						item.write(file);
						category.setIcon("category/" + fileName);
					} else { 
						category.setIcon(null);
					}
				}
			}
			categoryDAO.edit(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
