package com.indiantrait.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indiantrait.customer.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);

}
