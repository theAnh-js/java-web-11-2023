package com.laptrinhspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhspringboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
