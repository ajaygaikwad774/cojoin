package com.smn.el.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.smn.el.model.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>{

	public Optional<User> findByEmail(String email);
	
    public User findByEmailAndEncPasswordAndRoleAndStatus(String email,String encPassword, String role, String status);
    
    
}
