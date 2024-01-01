package com.cms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.Models.User;
import com.cms.Services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cms/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String adduser(@RequestBody User user) {
		
		return userService.registerUser(user);
	 
	}
	
}
