package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.CategoryService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/thoat" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// @Inject
	private ICategoryService categoryService = new CategoryService();
	private INewsService newsService = new NewsService();
	private IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			// RequesetDispatcher.forward -> trả về tài nguyên một cách ngầm, ko hiện lên url
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			// nếu nhấn vào logout thì -> remove session
			SessionUtil.getInstance().removeValue(req, "user");
			// resp.sendRedirect(req.getContextPath() + "/trang-chu");
			// Nếu nhấn thoát thì ta chuyển hướng đến /trang-chu,
			// nhưng mình sẽ cho nó chuyển hướng đến trang đăng nhập :))
			resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login");  // sendRedirect Hiện trên url
		} else {
			req.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			// Có thể dùng FormUtil.toModel(UserModel.class, req);
			// để mapping tham số trên url vào các thuộc tính của đối tượng
			// nhưng do thư viện không ăn nên tạm thời dùng cách truyền thống :))
			UserModel user = new UserModel();
			String username = req.getParameter("userName"); // get từ input username ở form login
			String password = req.getParameter("password");// get từ input password ở form login
			user.setUserName(username);
			user.setPassword(password);

			user = userService.findByUserNameAndPasswordAndStatus(user.getUserName(), user.getPassword(), 0);
			
			if (user != null) {		
				SessionUtil.getInstance().putValue(req, "user", user);
				if (user.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				} else if (user.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
				
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login");
			}
		}
	}

}