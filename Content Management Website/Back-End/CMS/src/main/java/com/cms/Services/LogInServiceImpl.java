package com.cms.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.UserDao;
import com.cms.Models.User;

@Service
public class LogInServiceImpl implements LogInService{
	
	@Autowired
	private UserDao udao;
	
	public String validateEmail(String email){
		
		User user = udao.findByEmail(email);
		
		if(user != null)
			return "User Found";
		else
			return "User Not Found";
	}

}
