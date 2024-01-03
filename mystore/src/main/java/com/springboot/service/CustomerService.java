package com.springboot.service;

import java.util.List;

import com.springboot.entity.Customer;

public interface CustomerService {

	Customer findById(Integer id);

	List<Customer> findAll();

	Customer create(Customer entity);

	void update(Customer entity);

	Customer delete(Integer id);
}
