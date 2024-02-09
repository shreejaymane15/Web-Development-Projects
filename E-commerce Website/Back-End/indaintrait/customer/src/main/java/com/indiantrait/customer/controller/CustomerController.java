package com.indiantrait.customer.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.indiantrait.customer.dto.ResponseDto;
import com.indiantrait.customer.model.User;
import com.indiantrait.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/indiantrait/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello Welcome to Daily Code Buffer!!";
    }
	
}
