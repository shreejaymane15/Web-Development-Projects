package com.cms.Services;

import com.cms.Dto.ResponseDto;
import com.cms.Models.User;

public interface UserService {
	
	public ResponseDto registerUser(User user);
	
	public ResponseDto loginUser(User user);
	
	public User findbyEmail(User user);


}
