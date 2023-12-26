package com.laptrinhspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhspringboot.model.ECommerce;
import com.laptrinhspringboot.repository.ECommerceRepository;

@Service
public class ECommerceServiceImpl implements IECommerceService{

	@Autowired
	ECommerceRepository eCommerceRepository;
	
	@Override
	public List<ECommerce> findAll() {
		return eCommerceRepository.findAll();
	}

}
