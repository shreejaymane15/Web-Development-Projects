package com.cms.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.UserDao;
import com.cms.Models.User;

@Service
public class UserServicesImpl implements UserService{
	
	@Autowired
	private UserDao udao;
	
	@Override
	public String registerUser(User user) {
		
		try {
			udao.save(user);
			return "User Registered Successfully";			
		}catch(Exception e) {
			return "Something Went Wrong";
		}
	}
	
	
	
}
