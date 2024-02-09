package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.entity.User;
import com.sample.repository.CustomerRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository custRepo;
	
	
	
	
	public CustomUserDetailsService() {
		super();
	}




	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = custRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		return new CustomUserDetails(user);
	}

}
