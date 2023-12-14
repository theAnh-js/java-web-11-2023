package DiamonShop.Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Dto.CartDto;
import DiamonShop.Dto.ItemForCartDto;
import DiamonShop.Dto.ProductDto;

@Controller
@RequestMapping("/gio-hang")
public class CartController extends BaseController {

	@GetMapping("/chi-tiet-gio-hang")
	public ModelAndView showCart() {
		ModelAndView mv = new ModelAndView("user/products/cart");
		return mv;
	}

	@PostMapping("/them-san-pham/{id}")
	public String addItem(HttpServletRequest request, @PathVariable int id) {
		CartDto cart = null;
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("cart");

		if (obj == null) {
			cart = new CartDto();
		} else {
			cart = (CartDto) obj;
		}		
		ProductDto product = homeService.getProductById(id);
		ItemForCartDto item = new ItemForCartDto(product, 1, product.getPrice());
		
		cart.addItemToList(item);
		
		session.setAttribute("cart", cart);
		session.setAttribute("size", cart.getList().size());
		

		double totalPriceOfCart = cart.getTotalPriceOfCart(cart.getList());
		session.setAttribute("totalPriceCart", totalPriceOfCart);

		return "redirect:/trang-chu";
	}
	
	@GetMapping("/chinh-sua-gio-hang/{num}/{id}")
	public String changeQuantity(HttpServletRequest request,@PathVariable int num, @PathVariable int id) {
		HttpSession session = request.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");
		if(cart == null) {
			cart = new CartDto();
		}
		
		ProductDto product = homeService.getProductById(id);
		ItemForCartDto item = cart.getItemById(id);
		if(num == -1) {
			if(item.getTotalQuantity() <= 1) {
				cart.removeItemById(id);

			}else {
				item.setTotalQuantity(item.getTotalQuantity() - 1);
				item.setTotalPrice(item.getTotalQuantity() * product.getPrice());
			}
					
		}else if(num == 1) {
			item.setTotalQuantity(item.getTotalQuantity() + 1);
			item.setTotalPrice(item.getTotalQuantity() * product.getPrice());
		}
		
		session.setAttribute("cart", cart);
		session.setAttribute("size", cart.getList().size());
		
		double totalPriceOfCart = cart.getTotalPriceOfCart(cart.getList());
		session.setAttribute("totalPriceCart", totalPriceOfCart);
		
		return "redirect:/gio-hang/chi-tiet-gio-hang";
	}
	
	@GetMapping("/xoa-khoi-gio-hang/{id}")
	public String deleteFromCart(HttpServletRequest request, @PathVariable int id) {
		HttpSession session = request.getSession(true);
		CartDto cart = (CartDto) session.getAttribute("cart");
		if(cart == null) {
			cart = new CartDto();
		}
		
		cart.removeItemById(id);
		
		session.setAttribute("cart", cart);
		session.setAttribute("size", cart.getList().size());
		
		double totalPriceOfCart = cart.getTotalPriceOfCart(cart.getList());
		session.setAttribute("totalPriceCart", totalPriceOfCart);
		
		return "redirect:/gio-hang/chi-tiet-gio-hang";
	}
	
}
