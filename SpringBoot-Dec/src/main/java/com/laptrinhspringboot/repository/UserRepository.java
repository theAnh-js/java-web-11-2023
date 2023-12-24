package com.laptrinhspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhspringboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Query("SELECT u FROM UserEntity u WHERE email = :email")
	public UserEntity findByEmail(@Param("email") String email);
}
