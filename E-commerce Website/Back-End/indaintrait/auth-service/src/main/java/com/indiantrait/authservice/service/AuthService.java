package com.indiantrait.authservice.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.indiantrait.authservice.event.RegistrationCompleteEvent;
import com.indiantrait.authservice.dto.ResponseDto;
import com.indiantrait.authservice.entity.Role;
import com.indiantrait.authservice.entity.User;
import com.indiantrait.authservice.entity.VerificationToken;
import com.indiantrait.authservice.repository.UserRepository;
import com.indiantrait.authservice.repository.VerificationTokenRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepo;
	
	
	public ResponseDto registerUser(User user, final HttpServletRequest request) {
		try {
			User checkCustomer = userRepo.findByEmail(user.getEmail()).orElse(null);
			if(checkCustomer == null){
//				customer.setImage("https://cdn-icons-png.flaticon.com/128/3135/3135715.png");
				user.setRole(Role.USER);
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepo.save(user);
				publisher.publishEvent(new RegistrationCompleteEvent(
						user,
						applicationUrl(request)));
				return new ResponseDto("User Registered Successfully", 200, 0L, null);
			}else {
				return new ResponseDto("User Already Exist", 501, 0L, null);
			}
			
		}catch(Exception e) {
			return new ResponseDto("Registration Failed", 500, 0L, null);			
		}
	}
	
	
	private String applicationUrl(HttpServletRequest request) {
		return "http://" + 
				request.getServerName() + 
				":" + 
				request.getServerPort() +
				request.getContextPath();
	}
	
	
	public void saveVerificationTokenForUser(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(user, token);
		verificationTokenRepo.save(verificationToken);
	}
	
	public ResponseDto validateVerificationToken(String token) {
		ResponseDto response = new ResponseDto();
		Calendar calender = Calendar.getInstance();
		VerificationToken verificationToken = 
				verificationTokenRepo.findByToken(token);
		
		if(verificationToken == null){
			response.setMessage("Invalid Token");
			System.out.println(response.getMessage());
			response.setStatus(501);
			return response;	
		}
		
		User user = verificationToken.getUser();
		
		if(user.isEnabled()) {
			response.setMessage("Already Verified");
			System.out.println(response.getMessage());
			response.setStatus(502);
			return response;
		}
		
		if((verificationToken.getExpirationTime().getTime()
				- calender.getTime().getTime())<= 0) {
			verificationTokenRepo.delete(verificationToken);
			response.setMessage("Token Expired");
			System.out.println(response.getMessage());
			response.setStatus(502);
			return response;	
		}
		
		user.setEnabled(true);
		userRepo.save(user);
		response.setMessage("Email Verified");
		System.out.println(response.getMessage());
		response.setStatus(200);
		return response;
	}
}
