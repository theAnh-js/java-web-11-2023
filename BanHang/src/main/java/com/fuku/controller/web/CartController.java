package com.fuku.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuku.dao.CategoryDAO;
import com.fuku.dao.ProductDAO;
import com.fuku.model.CartModel;
import com.fuku.model.ItemModel;
import com.fuku.model.ProductModel;
import com.fuku.model.UserModel;

@WebServlet(urlPatterns = { "/cart" })
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 2166171402445505208L;
	CategoryDAO categoryDAO = new CategoryDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// lấy user ra từ session được tạo bên LoginController
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		req.setAttribute("userFromSession", user);
		req.setAttribute("listCategory", categoryDAO.findAllCategories());

		RequestDispatcher rd = req.getRequestDispatcher("/views/cart.jsp");
		rd.forward(req, resp);
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

		String productID = req.getParameter("idforcart");
		if (productID != null) {
			ProductDAO productDAO = new ProductDAO();
			ProductModel product = productDAO.findProductById(Integer.parseInt(productID));
			double newPrice = product.getPrice(); // giả sử mỗi sản phẩm sẽ tăng 20% giá thì *1.2

			ItemModel item = new ItemModel(product, 1, newPrice);
			cart.addItem(item);
		}

		session.setAttribute("cart", cart);

		double totalMoney = cart.getTotalMoney();
		session.setAttribute("totalMoney", totalMoney);

		List<ItemModel> list = cart.getListItem();
		session.setAttribute("size", list.size());

		
		resp.sendRedirect(req.getContextPath() + "/home");
	}
}
