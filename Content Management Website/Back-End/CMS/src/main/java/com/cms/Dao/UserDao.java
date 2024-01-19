package com.cms.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.Models.User;

public interface UserDao extends JpaRepository<User, Long>{

	public Optional<User> findByEmail(String email);
	
}
