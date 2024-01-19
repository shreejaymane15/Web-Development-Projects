package com.cms.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.UserDao;
import com.cms.Dto.LogInDto;
import com.cms.Models.User;

@Service
public class LogInServiceImpl implements LogInService{
	
	@Autowired
	private UserDao udao;
	
	public String validateLogIn(LogInDto login){
		
		User user = udao.findByEmail(login.getEmail()).orElse(null);
		
		
		try{			
			if(user.getPassword() == login.getPassword()) {
				
				
			}else{
				
			}
		}catch(Exception e){
			
		}
		return "";
			
	}

}
