package com.cms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.Dto.LogInDto;
import com.cms.Dto.ResponseDto;
import com.cms.Dto.SignInResponseDto;
import com.cms.Services.LogInService;
import com.cms.Utility.JwtUtils;

@CrossOrigin("*")
@RestController
@RequestMapping("/cms/auth")
public class AuthController {
	
	
	@Autowired
	private LogInService lser;
	
	@Autowired
	public AuthenticationManager amgr;

	@Autowired
	public JwtUtils jwtutil;
		
	@PostMapping("/login")
	public ResponseEntity<?> logIn(@RequestBody LogInDto cred) {
		
		System.out.println("in sign in " + cred);
		
		UsernamePasswordAuthenticationToken unauthorizedUser = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword());
		Authentication authorizedUser = amgr.authenticate(unauthorizedUser);
		
		long userId = lser.validateAndLogin(cred);
		
		if(userId == -1) {
			return ResponseEntity.ok(
					new ResponseDto("Email id not registered", 500, -1L, null));
		}else if(userId == 0) {
			return ResponseEntity.ok(
					new ResponseDto("Password is wrong", 500, 0L, null));
		}else {
			String jwtToken = jwtutil.generateJwtToken(authorizedUser);
			
			return ResponseEntity.ok(
					new ResponseDto("User authentication success!!!", 200, userId, jwtToken));
		}
	}
	

}
