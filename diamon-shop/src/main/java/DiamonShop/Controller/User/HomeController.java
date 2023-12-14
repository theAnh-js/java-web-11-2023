package DiamonShop.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.SystemContant.Contants;

@Controller
public class HomeController extends BaseController {
	
	
	@RequestMapping(value = {"/", "/trang-chu"})
	public ModelAndView Index() {
		
		sharedMv.addObject("slides", homeService.getDataSlides());
		sharedMv.addObject("categories", homeService.getAllCategory());
		sharedMv.addObject("newProducts", homeService.getAllProduct(Contants.NO, Contants.YES));
		sharedMv.addObject("highlightProducts", homeService.getAllProduct(Contants.YES, Contants.NO));
		
		sharedMv.setViewName("user/index");	
		return sharedMv;
	}
	
	@RequestMapping(value = {"/danh-sach-san-pham/{categoryId}/{page}"})
	public ModelAndView product(@PathVariable int categoryId, @PathVariable int page) {
		ModelAndView mv = new ModelAndView("user/products/productByCategory");
		//mv.addObject("productList", homeService.getProductByCategoryId(categoryId));
		int limit = 3;
		mv.addObject("categoryId", categoryId);
		mv.addObject("currentPage", page);
		mv.addObject("totalPage", homeService.getTotalPageByCategoryId(homeService.getProductByCategoryId(categoryId).size(), limit));
		mv.addObject("productsByCateIdAndPaging", homeService.getProductByCategoryIdAndPaging(categoryId, page, limit));
		return mv;
	}
	
	
}
