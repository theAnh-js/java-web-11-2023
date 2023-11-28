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
import com.fuku.dao.ProductDAO;
import com.fuku.model.CategoryModel;
import com.fuku.model.ProductModel;
import com.fuku.util.Constant;

@WebServlet(urlPatterns = { "/admin/product/add" })
public class ProductAddController extends HttpServlet {

	private static final long serialVersionUID = -1936849918335372868L;
	CategoryDAO categoryDAO = new CategoryDAO();
	ProductDAO productDAO = new ProductDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<CategoryModel> listCate = categoryDAO.findAllCategories();
		req.setAttribute("listCate", listCate);

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/add-product.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel product = new ProductModel();

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
					product.setProductID(Integer.parseInt(item.getString()));

				} else if (item.getFieldName().equals("name")) {
					product.setProductName(item.getString("UTF-8"));
				}else if (item.getFieldName().equals("description")) {
					product.setDescription(item.getString("UTF-8"));
				} else if (item.getFieldName().equals("imageLink")) {
					System.out.println(item.getName());

					if (item.getSize() > 0) {
						String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);

						String fileName = System.currentTimeMillis() + "." + ext;

						File file = new File(Constant.DIR + "/product/" + fileName);

						item.write(file);

						product.setImageLink("product/" + fileName);

					} else {
						product.setImageLink(null);
					}

				} else if (item.getFieldName().equals("category")) {
					product.setCategoryID(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("price")) {
					product.setPrice(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("sellerID")) {
					product.setSellerID(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("amount")) {
					product.setAmount(Integer.parseInt(item.getString()));
				}
			}
			
			productDAO.insert(product);
			resp.sendRedirect(req.getContextPath() + "/admin/product/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
