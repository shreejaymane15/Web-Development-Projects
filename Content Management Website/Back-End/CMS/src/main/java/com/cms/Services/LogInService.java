package com.cms.Services;

import com.cms.Dto.LogInDto;
import com.cms.Models.User;

public interface LogInService {
	
	public String validateLogIn(LogInDto login);

	public User validateEmail(String email);
	
	public long validateAndLogin(LogInDto cred);
	
}
