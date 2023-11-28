package com.fuku.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuku.dao.OrderDAO;
import com.fuku.model.CartModel;
import com.fuku.model.UserModel;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckOutController extends HttpServlet {
	
	private static final long serialVersionUID = 2023537037944700518L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		CartModel cart = null;
		HttpSession session = req.getSession(true);
		Object o = session.getAttribute("cart");
		if(o != null) {
			cart = (CartModel)o;
		}else {
			cart = new CartModel();
		}
		
		UserModel user = (UserModel)session.getAttribute("user");
		if(user != null) {
			OrderDAO order = new OrderDAO();
			order.insertOrder(user, cart);
			
			session.removeAttribute("cart");
			session.setAttribute("size", 0);
			session.setAttribute("totalMoney", 0);
			
			req.setAttribute("userFromSession", user);
			
			resp.sendRedirect(req.getContextPath() + "/cart");
		}else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
