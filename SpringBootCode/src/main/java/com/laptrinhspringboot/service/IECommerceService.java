package com.laptrinhspringboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laptrinhspringboot.model.ECommerce;

@Service
public interface IECommerceService {

	List<ECommerce> findAll();
}
