package DiamonShop.Controller.User;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Entity.Menu;
import DiamonShop.Service.User.IHomeService;

public class BaseController {
	
	@Autowired
	IHomeService homeService;
	
	public ModelAndView sharedMv = new ModelAndView();
	
	@PostConstruct
	public ModelAndView init() {
		List<Menu> listMenu = homeService.getAllMenu();
		sharedMv.addObject("menus", listMenu);
		
		return  sharedMv;
	}

}
