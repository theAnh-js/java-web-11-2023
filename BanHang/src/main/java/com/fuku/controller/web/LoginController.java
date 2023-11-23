package com.fuku.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuku.dao.LoginDAO;
import com.fuku.model.UserModel;
import com.mysql.cj.Session;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -7238581770159718375L;

	private LoginDAO loginDAO = new LoginDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/decorators/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UserModel user = loginDAO.checkUserByUsernameAndPassword(username, password);
		if (user == null) {
			req.setAttribute("message", "Invalid Account");
			RequestDispatcher rd = req.getRequestDispatcher("/decorators/login.jsp");
			rd.forward(req, resp);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect(req.getContextPath() + "/home");
		}

	}
}
