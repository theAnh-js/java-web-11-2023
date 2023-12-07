package com.fuku.admin.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuku.dao.CategoryDAO;
import com.fuku.model.CategoryModel;
import com.fuku.util.HttpUtil;

// Dùng JackSon
@WebServlet(urlPatterns = {"/api-admin-category"}) // /api...để không áp dụng giao diện với servlet này(đã cài trong decorators.xml)
public class CategoryAPIController extends HttpServlet {

	private static final long serialVersionUID = -7616457167148878633L;
	CategoryDAO categoryDAO = new CategoryDAO();
	
	// Lấy ra danh sách category
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
	
		List<CategoryModel> categoryModel = categoryDAO.findAllCategories();
		mapper.writeValue(resp.getOutputStream(), categoryModel); // java value(ở đây là danh sách category) -> json
        // ttpServletResponse trong Java Servlet, 
		// và nó được sử dụng để nhận một OutputStream. 
		//OutputStream là một đối tượng đầu ra được sử dụng để 
		// ghi dữ liệu từ servlet đến client, thường là trình duyệt web.	
		
		/*
		 * mapper.writeValue(...) là phương thức của một đối tượng ObjectMapper (có thể
		 * là Jackson ObjectMapper). Trong ngữ cảnh này, nó được sử dụng để chuyển đối
		 * tượng categoryModel thành định dạng dữ liệu nào đó (có thể là JSON), và sau
		 * đó ghi nó vào OutputStream. Điều này sẽ đưa dữ liệu về client thông qua phản
		 * hồi HTTP.
		 */
	}
	
	// Thêm category mới
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		
		categoryDAO.insert(categoryModel);
		mapper.writeValue(resp.getOutputStream(), "{Đã thêm thành công!}");
		
	}
	
	// Sửa thông tin của 1 category nào đó.
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		
		categoryDAO.edit(categoryModel);
		mapper.writeValue(resp.getOutputStream(), "{Đã sửa thành công!}");
		
		/*
		 * Luồng: postman đóng vai trò như 1 client, từ postman gửi 1 category được sửa
		 * qua HTTP bằng phương thức PUT lên server. Sau đó server(là servlet này) nhận
		 * được thông qua req.getReader() Vì lúc này, category đó đang ở dạng json nên
		 * cần chuyển nó sang java value thông qua hàm toModer(); Sau khi đã chuyển sang
		 * java value rồi thì truyền vào hàm edit() như bình thường thoi. Sau đó, có thể
		 * dùng mapper.writeValue() để chuyển lại 1 chuỗi nào đó thành json rồi ghi vào
		 * outPutStream(), sau đó dùng resp.getOutputStream() để trả json đó về lại
		 * client.
		 */
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		
		categoryDAO.delete(categoryModel.getCategoryID());
		mapper.writeValue(resp.getOutputStream(), "{Đã xóa thành công!}");
	}
}
