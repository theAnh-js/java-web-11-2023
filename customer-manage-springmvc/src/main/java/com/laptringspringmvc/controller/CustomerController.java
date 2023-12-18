package com.laptringspringmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptringspringmvc.entity.Customer;
import com.laptringspringmvc.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@RequestMapping("/")
	public ModelAndView home() {
		
		ModelAndView mav = new ModelAndView("index");
		
		List<Customer> listCustomer = customerService.listAll();
		
		mav.addObject("listCustomer", listCustomer);
		return mav;
	}
	
	@RequestMapping("/new")
	public String showAddNewCustomerForm(Map<String, Object> model) {
		model.put("customer", new Customer());
		return "new_customer";
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
	
		customerService.save(customer);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public ModelAndView showEditCustomer(@RequestParam int id) {
		
		ModelAndView mav = new ModelAndView("edit_customer");
		Customer customer = customerService.getCustomerById(id);
		mav.addObject("customer", customer);
		
		return mav;
	}
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam int id) {	
		customerService.deleteCustomerById(id);
		return "redirect:/";
	}
	
	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String keyword) {
		ModelAndView mav = new ModelAndView("search");
		List<Customer> list = customerService.search(keyword);
		mav.addObject("customerList", list);	
		return mav;
	}
}
