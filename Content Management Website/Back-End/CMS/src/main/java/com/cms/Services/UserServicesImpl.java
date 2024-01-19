	package com.cms.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.Dao.UserDao;
import com.cms.Dto.ResponseDto;
import com.cms.Models.Role;
import com.cms.Models.User;
import com.cms.Utility.Utilities;

@Service
public class UserServicesImpl implements UserService{
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private Utilities utility; 
	
	
	
	@Override
	public ResponseDto registerUser(User user) {
		ResponseDto response = new ResponseDto();	
		try {
			User checkUser = udao.findByEmail(user.getEmail()).orElse(null);
			if(checkUser == null){
				user.setImage("https://cdn-icons-png.flaticon.com/128/3135/3135715.png");
				user.setRole(Role.ROLE_USER);
				String salt = utility.generateSaltValue(6);
				user.setSalt(salt);
				String password = user.getPassword();
				user.setPassword(utility.encryptPassword(password, salt));
				udao.save(user);
				response.setMessage("User Registered Successfully");
				response.setStatus(200);
				return response;
			}else {
				response.setMessage("User Already Exist");
				response.setStatus(500);
				return response;				
			}
			
		}catch(Exception e) {
			response.setMessage("Registration Failed");
			response.setStatus(500);
			return response;							
		}
	}

	@Override
	public ResponseDto loginUser(User user) {
		ResponseDto response = new ResponseDto();
		try {
			User checkUser = udao.findByEmail(user.getEmail()).orElse(null);
			if(utility.verifyUserPassword(user.getPassword(), checkUser.getPassword(), checkUser.getSalt())) {
//				String token = jwt.createToken(checkUser);
//				checkUser.setToken(token);
//				udao.save(checkUser);
//				response.setToken(token);
				response.setUser_id(checkUser.getId());
				response.setStatus(200);
				response.setMessage("User Logged In Successfully");
				return response;
			}else {
				response.setStatus(500);
				response.setMessage("Invalid Password");
				return response;				
			}
		}catch(Exception e){
			response.setStatus(500);
			response.setMessage("Invalid Email Id");
			return response;							
		}
	}
	
	
	@Override
	public User findbyEmail(User user) {
		User getUser = udao.findByEmail(user.getEmail()).orElse(null);
		return getUser;
	}
	
	
	
}
