package com.laptrinhspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhspringboot.model.Blog;
import com.laptrinhspringboot.model.ECommerce;
import com.laptrinhspringboot.service.IBlogService;
import com.laptrinhspringboot.service.IECommerceService;

@Controller
public class BlogController {

	@Autowired
	IBlogService blogService;
	
	@Autowired
	IECommerceService eCommerceService;
	
	@GetMapping("")
	public String showList(){
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String search(Optional<String> authorName, 
						 Optional<Integer> ecomId,
						 @RequestParam(defaultValue = "1") int page,
						 Model model) {
		
		int limit = 2;
		Page<Blog> blogList = blogService.findAllBlogPaging(PageRequest.of(page - 1,limit));
		
		/*
		 * if(authorName.isEmpty()) { blogList = null; }
		 */
		if(authorName.isPresent() && ecomId.isPresent()) {
			blogList = blogService.findAllBlogPagingByEcomAndAuthorName(ecomId.get(), authorName.get(), PageRequest.of(page - 1, limit));
		}else if(authorName.isPresent()) {
			// authorName đang thuộc kiểu Optional nên .get() để lấy ra giá trị String của nó.
			blogList = blogService.findBlogPagingByAuthorName(authorName.get(), PageRequest.of(page - 1, limit));
		}else if(ecomId.isPresent()) {
			blogList = blogService.findAllBlogPagingByECommerce(ecomId.get(), PageRequest.of(page - 1, limit));
		}
		
		Integer icommerceId = ecomId.isPresent() ? ecomId.get() : null; // vì  ecomId.get() no present mà cố .get() sẽ bị lỗi
		String auName = authorName.isPresent() ? authorName.get() : "";
		
		List<ECommerce> ecommerceList = eCommerceService.findAll();		
		model.addAttribute("ecommerceList", ecommerceList);
			
		model.addAttribute("blogList", blogList);
		model.addAttribute("ecomID", icommerceId); // truyền sang để th:selected = "${ecomID == ecommerce.id}" thẻ option
		model.addAttribute("authorNameValue", auName); // truyền sang để th:value="${authorNameValue}", hiện thị lại tên author sau khi submit
		
		return "list"; 
		
	}
	
	@GetMapping("/403")
	public String accessDeniedPage() {
		return "/403";
	}
}
