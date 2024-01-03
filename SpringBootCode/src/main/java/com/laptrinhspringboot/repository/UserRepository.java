package com.laptrinhspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhspringboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
