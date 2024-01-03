package com.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.entity.Customer;
import com.springboot.repository.CustomerRepository;
import com.springboot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	

	@Override
	public Customer findById(Integer id) {
		
		Optional<Customer> Customer = customerRepository.findById(id);
		if(Customer.isPresent()) {
			return Customer.get();
		}
		return null;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer create(Customer entity) {
		return customerRepository.save(entity);
	}

	@Override
	public void update(Customer entity) {
		
		Customer existCustomer = findById(entity.getId());
		if(existCustomer != null) {
			existCustomer.setId(entity.getId());
			existCustomer.setFullName(entity.getFullName());
			existCustomer.setEmail(entity.getEmail());
			existCustomer.setActivated(entity.getActivated());
			existCustomer.setAdmin(entity.getAdmin());
			existCustomer.setPassword(entity.getPassword());
			existCustomer.setPhoto(entity.getPhoto());
			
			customerRepository.save(existCustomer);
		}
		
	}

	@Override
	public Customer delete(Integer id) {
		
		Customer deleteCustomer = findById(id);	
		if(deleteCustomer != null){
			customerRepository.delete(deleteCustomer);
			return deleteCustomer;
		}else {
			return null;
		}
		
	}

}
