package com.cms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.Dto.ResponseDto;
import com.cms.Models.User;
import com.cms.Services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cms")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/register")
	public ResponseDto adduser(@RequestBody User user) {
		
		return userService.registerUser(user);
	 
	}
	
	
//	@PostMapping("/user/login")
//	public ResponseDto checkUser(@RequestBody User user) {
//		
//		return userService.loginUser(user);
//	 
//	}
	
	@PostMapping("/user/test")
	public String testUser(@RequestBody String str) {
		return str;
	}
	
	
}
