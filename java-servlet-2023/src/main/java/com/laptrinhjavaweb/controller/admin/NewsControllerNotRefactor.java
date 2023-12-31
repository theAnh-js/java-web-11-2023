package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-news(no refactor)" })
public class NewsControllerNotRefactor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	INewsService newsService = new NewsService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// CÁCH 1: lấy các giá trị từ url rồi set vào model để tạo 1 model.
		// do ta đang làm theo hướng controller - model - view
		// nên ta set list vào model ở controller rồi truyền model qua view
		NewsModel news = new NewsModel();

		String pageStr = req.getParameter("page"); // lấy page qua url, page ở đấy chính là số thứ tự của page ta click
													// đến
		String maxPageItemStr = req.getParameter("maxPageItem"); // lay maxPageItem qua url
		// set page và maxPageItem vào model news
		if (pageStr != null) {
			news.setPage(Integer.parseInt(pageStr));
		} else {
			news.setPage(1);
		}

		if (maxPageItemStr != null) {
			news.setMaxPageItem(Integer.parseInt(maxPageItemStr));
		} else {
			news.setMaxPageItem(1);
		}

		// CÁCH 2: Dùng BeanUtils.populate(obj, req.getParameterMap()); bên class
		// FormUtil.java
		// Ưu điểm của cách này là dù có nhiều parameter cần lấy thì chỉ cần 
		// thằng bên FormUtil cũng map vào cho mình. -> ngắn gọn, tự động.
		// VÀ không cần chuyển đổi kiểu dữ liệu String -> kiểu mong muốn.
//		try {
//			NewsModel news = FormUtil.toModel(NewsModel.class, req);
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}

		// cách tính offset(địa chỉ bắt đầu của trang): offset = limit * (target page
		// number - 1)
		Integer offset = news.getMaxPageItem() * (news.getPage() - 1);
	//	news.setList(newsService.findAll(offset, news.getMaxPageItem()));
		// news.setTotalItem(news.getList().size()); // vì ta setList ở trên rồi nên có
		// thể getList().size ở đây để lấy ra tất cả số lượng news
		news.setTotalItem(newsService.getTotalItem()); // nhưng ta nên xây dựng 1 hàm để chuyển đếm thôi, sẽ đỡ nặng, ta
														// xây thêm hàm getTotalItem()
		news.setTotalPage((int) Math.ceil((double) news.getTotalItem() / news.getMaxPageItem()));
		// getTotalItem lấy từ findAll.size();
		// getMaxPageItem lấy từ parameter truyền lên.

		req.setAttribute(SystemConstant.MODEL, news);

		// = req.setAttribute("model", news);
		// vì lúc nào controller cũng trả về view key có tên là model
		// nên ta đặt cho nó thành hằng số trong lớp SystemContant luôn.

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/news/list.jsp");
		rd.forward(req, resp);
	}

}
