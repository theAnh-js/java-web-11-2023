package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-admin-new" })
public class NewApi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	// ServletException, IOException {
	//
	// }

	private INewsService newService = new NewsService();

	// THÊM MỚI
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		// ===== chuyển từ json của client -> model =====
		// Đây là cách để ta map những value của key trong json vào các field của model.
		NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		// hàm of trả về 1 đối tượng HttpUtil nên ta gọi luôn hàm toModel được.
		// HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		// có tác dụng convert dữ liệu JSON sang 1 đối tượng, cụ thể ở đây là đối tượng
		// NewsModel.
		// NewsModel.class là kiểu đối tượng mà ta muốn chuyển đổi dữ liệu JSON thành.

		// lưu model xuống database
		
		newsModel = newService.save(newsModel);

		// ===== chuyển từ model sang json để thằng server trả về cho client =====
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), newsModel);
	}

	// CẬP NHẬT
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel updatedNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		//Từ req.getReader() trả về BufferedReader

		updatedNews = newService.update(updatedNews);
		mapper.writeValue(resp.getOutputStream(), updatedNews);
	}

	//XÓA
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel updatedNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
	
		newService.delete(updatedNews.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");  // xóa nên trả về api chuỗi rỗng thôi
	}
}
