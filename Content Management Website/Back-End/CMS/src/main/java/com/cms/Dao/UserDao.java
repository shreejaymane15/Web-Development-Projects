package com.cms.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.Models.User;

public interface UserDao extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
}
