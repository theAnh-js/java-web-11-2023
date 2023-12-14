package DiamonShop.Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import DiamonShop.Dto.CartDto;
import DiamonShop.Dto.ItemForCartDto;
import DiamonShop.Dto.ProductDto;

@Controller
@RequestMapping("/ajax-cart")
public class AjaxCartController extends BaseController {

    @PostMapping("/add-to-cart")
    @ResponseBody
    public String addToCart(HttpServletRequest request, @RequestParam("productId") int productId) {
    	CartDto cart = null;
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("cart");

		if (obj == null) {
			cart = new CartDto();
		} else {
			cart = (CartDto) obj;
		}		
		ProductDto product = homeService.getProductById(productId);
		ItemForCartDto item = new ItemForCartDto(product, 1, product.getPrice());
		
		cart.addItemToList(item);
		
		session.setAttribute("cart", cart);
		session.setAttribute("size", cart.getList().size());
		

		double totalPriceOfCart = cart.getTotalPriceOfCart(cart.getList());
		session.setAttribute("totalPriceCart", totalPriceOfCart);

		return "ok";
    }

    // Thêm các phương thức khác cho việc cập nhật và xoá sản phẩm khỏi giỏ hàng
}