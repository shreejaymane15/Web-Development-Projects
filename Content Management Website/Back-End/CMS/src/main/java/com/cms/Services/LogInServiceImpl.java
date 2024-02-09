package com.cms.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.Dao.UserDao;
import com.cms.Dto.LogInDto;
import com.cms.Models.User;

@Service
public class LogInServiceImpl implements LogInService{
	
	@Autowired
	private UserDao udao;

	@Autowired
	private PasswordEncoder pencoder;
	
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
				
	@Override
	public User validateEmail(String email) {
		
		User user = udao.findByEmail(email).orElse(null);
		
		if (user!=null)
			return user;
		else
			return null;
	}
	
	@Override
	public long validateAndLogin(LogInDto cred) {
		User u = validateEmail(cred.getEmail());
		if(u != null) {
			String enteredpassword = cred.getPassword();
			String soredPassword = u.getPassword();
			if(authenticateUser(enteredpassword, soredPassword)) {
				return u.getId();
			}else {
				return 0;
			}
		}else {
			return -1;
		}
	}

	private boolean authenticateUser(String pass1, String pass2) {
		return pencoder.matches(pass1, pass2);
	}

}
