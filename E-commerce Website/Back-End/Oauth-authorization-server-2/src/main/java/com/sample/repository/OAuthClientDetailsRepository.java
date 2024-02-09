package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.OAuthClientDetails;

public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, String>{

}
