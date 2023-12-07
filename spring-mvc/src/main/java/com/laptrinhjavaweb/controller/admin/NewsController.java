package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;

@Controller(value = "newsControllerOfAdmin") // chỉ cần 1 controller, có thể tạo nhiều request mapping
public class NewsController {
	
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showListNews(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpServletRequest request) {
		
		NewDTO newDTO = new NewDTO();	
		newDTO.setPage(page);
		newDTO.setLimit(limit);
		
		ModelAndView mav = new ModelAndView("admin/news/list");
		Pageable pageable = new PageRequest(page - 1, limit);
				
		newDTO.setTotalItem(newService.getTotalItem());
		newDTO.setTotalPage((int)Math.ceil((double)newDTO.getTotalItem() / newDTO.getLimit()));
		newDTO.setListResult(newService.findAll(pageable));
		
		if(request.getParameter("message") != null) {
			/*
			 * if(request.getParameter("message").equals("update_success")) {
			 * mav.addObject("message", "Update success"); mav.addObject("alert",
			 * "success"); }else
			 * if(request.getParameter("message").equals("insert_success")){
			 * mav.addObject("message", "Insert success"); mav.addObject("alert",
			 * "success"); }else if(request.getParameter("message").equals("error_system")){
			 * mav.addObject("message", "Error system"); mav.addObject("alert", "danger"); }
			 */		
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("danger"));
		}
		
		mav.addObject("model", newDTO);
		return mav;
	}
	
	// thằng url này vừa có chức năng thêm bài viết vừa chỉnh sửa bài viết
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNews(@RequestParam(value="id", required = false) Long id, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("admin/news/edit");
		NewDTO model = new NewDTO();
		if(id != null) {
			model = newService.findById(id);
		}
		if(request.getParameter("message") != null) {
			/*
			 * if(request.getParameter("message").equals("update_success")) {
			 * mav.addObject("message", "Update success"); mav.addObject("alert",
			 * "success"); }else
			 * if(request.getParameter("message").equals("insert_success")){
			 * mav.addObject("message", "Insert success"); mav.addObject("alert",
			 * "success"); }else if(request.getParameter("message").equals("error_system")){
			 * mav.addObject("message", "Error system"); mav.addObject("alert", "danger"); }
			 */
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("danger"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
}
