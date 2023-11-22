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
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.impl.CategoryService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.sort.Sorter;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Nên áp dụng dependency injection vào
	// Nhưng thư viện không ăn, dùng tạm toán tử new zậy :))
	INewsService newsService = new NewsService();
	ICategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ĐÂY LÀ CÁCH ĐỂ REFACTOR
		// Xem bên class NewsControllerNotRefactor để hiểu hơn các dòng code
		// và biết được chỗ nào được refactor.
//		NewsModel news = null;
//		try {
//			news = FormUtil.toModel(NewsModel.class, req);
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}

		NewsModel model = new NewsModel();
		String pageStr = req.getParameter("page"); // lấy page qua url, page ở đấy chính là số thứ tự của page ta click
													// đến
		String maxPageItemStr = req.getParameter("maxPageItem"); // lay maxPageItem qua url
		// set page và maxPageItem vào model news
		if (pageStr != null) {
			model.setPage(Integer.parseInt(pageStr));
		} else {
			model.setPage(1);
		}

		if (maxPageItemStr != null) {
			model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
		} else {
			model.setMaxPageItem(1);
		}
		String type = req.getParameter("type");
		if(type != null) {
			model.setType(type);
		}
		
		String view = "";

		// nếu type là list thì -> show list
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));

//			Integer offset = news.getMaxPageItem() * (news.getPage() - 1);
			model.setList(newsService.findAll(pageble));
			model.setTotalItem(newsService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

			view = "/views/admin/news/list.jsp";
			
			//nếu là edit thì -> edit
		}else if(model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId() != null) {
				model = newsService.findOne(model.getId());
			}else {
				
			}
			req.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/news/edit.jsp";
		}
		
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);

	}

}
