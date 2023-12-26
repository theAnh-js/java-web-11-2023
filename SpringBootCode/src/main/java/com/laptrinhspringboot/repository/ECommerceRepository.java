package com.laptrinhspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhspringboot.model.ECommerce;

public interface ECommerceRepository extends JpaRepository<ECommerce, Integer> {

}
