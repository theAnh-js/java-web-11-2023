package com.fuku.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuku.dao.ProductDAO;
import com.fuku.model.CartModel;
import com.fuku.model.ItemModel;
import com.fuku.model.ProductModel;

@WebServlet(urlPatterns = { "/change" })
public class ChangeInforCartController extends HttpServlet {

	private static final long serialVersionUID = -4369484763695962941L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CartModel cart = null;
		HttpSession session = req.getSession(true);
		Object o = session.getAttribute("cart");
		if (o != null) {
			cart = (CartModel) o;
		} else {
			cart = new CartModel();
		}
		
		String numStr = req.getParameter("num").trim();
		String idStr = req.getParameter("id");
		int num, id;
		
		if(numStr != null && idStr != null) {
			num = Integer.parseInt(numStr);
			id = Integer.parseInt(idStr);
			
			if(num <= -1 && cart.getQuantityById(id) <= 1 ) {
				cart.removeItemById(id);
			}else {
				ProductDAO productDAO = new ProductDAO();
				ProductModel product = productDAO.findProductById(id);
				double price = product.getPrice();
				ItemModel item = new ItemModel(product, num, price);
				cart.addItem(item);		
			}
		}
		
		// sau khi change cần set lại cho session
		session.setAttribute("cart", cart);
		
		double totalMoney = cart.getTotalMoney();
		session.setAttribute("totalMoney", totalMoney);
		
		List<ItemModel> list = cart.getListItem();
		session.setAttribute("size", list.size());
		
		resp.sendRedirect(req.getContextPath() + "/cart");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CartModel cart = null;
		HttpSession session = req.getSession(true);
		Object o = session.getAttribute("cart");
		if (o != null) {
			cart = (CartModel) o;
		} else {
			cart = new CartModel();
		}

		int productID = Integer.parseInt(req.getParameter("id"));
		cart.removeItemById(productID);

		// sau khi change cần set lại cho session
		session.setAttribute("cart", cart);
		
		double totalMoney = cart.getTotalMoney();
		session.setAttribute("totalMoney", totalMoney);

		List<ItemModel> list = cart.getListItem();
		session.setAttribute("size", list.size());

		resp.sendRedirect(req.getContextPath() + "/cart");
	}

}
