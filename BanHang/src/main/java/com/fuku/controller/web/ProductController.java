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
import com.fuku.model.CategoryModel;
import com.fuku.model.ProductModel;
import com.fuku.model.UserModel;

@WebServlet(urlPatterns = { "/product" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = -5551982355769652668L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Thiet lap Tieng Viet
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		CategoryDAO categoryDAO = new CategoryDAO();
		ProductDAO productDAO = new ProductDAO();

		List<CategoryModel> listCategory = categoryDAO.findAllCategories();
		List<ProductModel> listAllPro = null;
		ProductModel lastPro = null;

		String categoryIDStr = req.getParameter("id");

		if (categoryIDStr == null) {  // khi ta bấm vào mục "sản phẩm" thì mới phân trang
			
			listAllPro = productDAO.findAllProduct();
			lastPro = productDAO.findLastProduct();
			
			//paging
			// lấy ra trang được click đến
			String pageStr = req.getParameter("page");		
			int page = 0;
			if(pageStr == null) { // nếu ko có thì mặc định nó là trang đầu tiên.
				page = 1;
			}else {
				page = Integer.parseInt(pageStr);
			}
			
			//Từ trang được click đó, lấy ra danh sách các sản phẩm tương ứng
			List<ProductModel> listProductForPaging = productDAO.findAllProductForPaging(page);	
			//Lấy ra tổng số sản phẩm ta có.
			int totalProduct = listAllPro.size();
			// Như vậy, nếu ta giới hạn 1 trang chỉ có tối đa 3 sản phẩm được hiện lên, thì ta sẽ có tổng số trang:
			int totalPage = totalProduct / 3;
			if(totalProduct % 3 != 0) { // Nếu totalProduct không chỉ hết cho 3 thì, ta tăng totalPage lên 1.
				totalPage ++;           // ví dụ totalProduct = 14 -> totalPage = 14/3 = 4, 4 trang chỉ chứa được 12 sản phẩm
			}						    // Nên ta tăng totalPage lên 1 = 5, để trang cuối(trang thứ 5) chứa nốt 2 sản phẩm còn lại.
			req.setAttribute("listAllProduct", listProductForPaging);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", page);//set lại sang bên jsp để + - khi bấm privious or next
			
			// Lấy ra danh sách categoryID trong danh sách truyền sang file product.jsp
			List<Integer> listCateID = categoryDAO.findCategoryIDInListProduct(listProductForPaging);
			req.setAttribute("categoryIDInListProduct", listCateID);
			
			
		} else { // Còn nếu ta bấm vào từng danh mục riêng thì không phân trang nữa
			int categoryID = Integer.parseInt(categoryIDStr);
			listAllPro = productDAO.findAllProductByCategoryId(categoryID);
			lastPro = productDAO.findLastProductInEachCategory(listAllPro);
			
			 req.setAttribute("listAllProduct", listAllPro);
		}

		req.setAttribute("listCategory", listCategory);
		req.setAttribute("lastProduct", lastPro);
		req.setAttribute("cate_ID", categoryIDStr);

		// lấy user ra từ session được tạo bên LoginController
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		req.setAttribute("userFromSession", user);
		
		RequestDispatcher pd = req.getRequestDispatcher("/views/product.jsp");
		pd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
