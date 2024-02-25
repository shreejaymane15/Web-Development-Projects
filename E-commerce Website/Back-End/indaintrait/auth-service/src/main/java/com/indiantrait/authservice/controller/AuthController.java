package com.indiantrait.authservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.indiantrait.authservice.dto.ResponseDto;
import com.indiantrait.authservice.entity.User;
import com.indiantrait.authservice.repository.UserRepository;
import com.indiantrait.authservice.service.AuthService;
import com.indiantrait.authservice.utility.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/indiantrait/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/login")
	public ResponseDto userLogin(@RequestBody User user) {
		try {
			User authorizedUser = userRepo.findByEmail(user.getEmail()).orElse(null);
	
				
			if(authorizedUser == null ) {
				return new ResponseDto("Email id not registered", 501, -1L, null);
				
			}else {
				
				UsernamePasswordAuthenticationToken unAuthorizedUser
				= new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
				
				Authentication authentication =  authenticationManager.authenticate(unAuthorizedUser);
				
				String jwtToken = jwtUtil.generateJwtToken(authentication);
				System.out.println("JWT TOKEN: " + jwtToken);
				
				return new ResponseDto("Login Successfull", 200, authorizedUser.getId(), jwtToken);
			}
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseDto("Bad Credentials", 500, -1L, null );
		}
	}
	
	@GetMapping("/verifyRegistration")
	public RedirectView verifyRegistration(@RequestParam String token) {
		return new RedirectView("http://localhost:3000/verification?token=" + token);
	}	
	
	@GetMapping("/emailVerification")
	public ResponseDto emailVerification(@RequestParam String token) {
			return authService.validateVerificationToken(token);
	}	
	
	@PostMapping("/register")
	public ResponseDto adduser(@RequestBody User user, final HttpServletRequest request) {
		
		return authService.registerUser(user, request);
	 
	}
	
	@GetMapping("/hello")
	public ResponseDto hello() {
		
		return new ResponseDto("Hello!!!!!", 200, 2L, null);
	 
	}
	
	@GetMapping("/hell")
	public String hell() {
		return "Hell";
	}
	
	
}
