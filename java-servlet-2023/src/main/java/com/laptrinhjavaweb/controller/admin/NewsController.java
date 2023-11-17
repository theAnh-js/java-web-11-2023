package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.sort.Sorter;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Nên áp dụng dependency injection vào
	// Nhưng thư viện không ăn, dùng tạm toán tử new zậy :))
	INewsService newsService = new NewsService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ĐÂY LÀ CÁCH ĐỂ REFACTOR 
		//Xem bên class NewsControllerNotRefactor để hiểu hơn các dòng code
		// và biết được chỗ nào được refactor.
//		NewsModel news = null;
//		try {
//			news = FormUtil.toModel(NewsModel.class, req);
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}

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
		
		Pageble pageble = new PageRequest(news.getPage(), news.getMaxPageItem(), 
				new Sorter(news.getSortName(), news.getSortBy()));

//		Integer offset = news.getMaxPageItem() * (news.getPage() - 1);
		news.setList(newsService.findAll(pageble));
		news.setTotalItem(newsService.getTotalItem());
		news.setTotalPage((int) Math.ceil((double) news.getTotalItem() / news.getMaxPageItem()));

		req.setAttribute(SystemConstant.MODEL, news);

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/news/list.jsp");
		rd.forward(req, resp);
		
		
	}

}
