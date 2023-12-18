package com.laptringspringmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptringspringmvc.entity.Customer;
import com.laptringspringmvc.repository.CustomerRepository;

@Service
public class CustomerService  {
	
	@Autowired
	private CustomerRepository repo;
	
	public List<Customer> listAll(){
		return (List<Customer>)repo.findAll();
	}
	
	public void save(Customer customer) {
		repo.save(customer);
	}
	
	public Customer getCustomerById(int id) {
		return repo.findOne(id);
	}
	
	public void deleteCustomerById(int id) {
		repo.delete(id);
	}
	
	public List<Customer> search(String keyword){
		return repo.search(keyword);
	}
}

