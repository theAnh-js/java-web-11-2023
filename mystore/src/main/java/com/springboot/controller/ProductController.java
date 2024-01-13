package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.entity.Category;
import com.springboot.entity.Product;
import com.springboot.service.CategoryService;
import com.springboot.service.CookieService;
import com.springboot.service.ProductService;

import jakarta.servlet.http.Cookie;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CookieService cookieService;
	
	@GetMapping("/by-category/{id}")
	public String productByCategory(@PathVariable("id") int id, Model model) {
		Category category = categoryService.findById(id);
		List<Product> productList = category.getProductList();
		
		model.addAttribute("productList", productList);
		return "category-detail";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("keyword") String keyword, Model model) {
		List<Product> productList = productService.findByName(keyword);
		model.addAttribute("productList", productList);
		return "index";
	}
	
	@GetMapping("/{id}")
	public String productDetail(@PathVariable("id") Integer id, Model model) {	
		
		Product product = productService.findById(id);
		
		Category cate = categoryService.findById(product.getCategory().getId());
		List<Product> sameProductList = cate.getProductList();
		
		// Danh sách sản phẩm yêu thích
		Cookie c = cookieService.read("favo");
		if(c != null) {
			String ids = c.getValue();
			// List<Product> listFavorate = productService.findByFavorate(ids);
			List<Product> listFavorate = productService.findByFavo(ids); // dùng hàm tự query
			model.addAttribute("listFavorate",listFavorate);
		}
		
		// gọi hàm lưu vào cookie, đồng thời lấy ra danh sách sản phẩm liên quan
		List<Product> watchedProds = productService.findWatchedProdList(id);
		model.addAttribute("watchedProds", watchedProds);
		
		// Tăng view nếu sản phẩm được xem
		int views = product.getViewCount() == null ? 0 : product.getViewCount();
		product.setViewCount(views + 1);
		productService.update(product);
		
		
		model.addAttribute("sameProductList",sameProductList); // có thể show ra các mặt hàng cùng loại
		model.addAttribute("product",product);
		return "product-detail";
	}
	
	@ResponseBody
	@GetMapping("/add-to-favorite/{id}")
	public boolean addToFavo(Model model, @PathVariable("id") Integer id) {
		Cookie favo = cookieService.read("favo");
		String value = id.toString();
		System.out.println("Value 69: " + value);
		if(favo != null) {
			value = favo.getValue();
			System.out.println("Value 72: " + value);
			if(!value.contains(id.toString())) {
				value += "," + id.toString();
			}else {
				return false;
			}
		}else if(favo == null){
			System.out.println("Favo is null");
		}
		
		cookieService.create("favo", value, 30);
		
		return true;
	}
	
	
	@GetMapping("/special/{id}")
	public String specialProduct(Model model, @PathVariable("id") int id) {
		List<Product> specialProductList = new ArrayList<>();
		int listSize = 6;
		switch (id) {
			case 1: {
				specialProductList = productService.findNewProduct(listSize);
				break;
			}
			case 2: {
				specialProductList = productService.findBestViewProduct(listSize);
				break;
			}
			case 3: {
				specialProductList = productService.findBestSellerProduct(listSize);
				break;
			}
			case 4: {
				specialProductList = productService.findBestDiscountProduct(listSize);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + id);
			}
		
			model.addAttribute("specialProductList", specialProductList);
			model.addAttribute("option", id);
			
			return "product-special";
		}
	
}
