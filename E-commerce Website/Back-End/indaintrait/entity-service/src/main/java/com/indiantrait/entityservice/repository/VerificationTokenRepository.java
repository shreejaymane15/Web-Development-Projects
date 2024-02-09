package com.indiantrait.entityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indiantrait.entityservice.entity.VerificationToken;


@Repository
public interface VerificationTokenRepository extends 
							JpaRepository<VerificationToken, Long>{

	public VerificationToken findByToken(String token);

}
