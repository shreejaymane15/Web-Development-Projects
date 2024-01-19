package com.cms.Dto;


public class LogInDto {
	
	private String email;
	private String password;
	
	
	

	
	public LogInDto() {
		super();
	}


	public LogInDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
		
	
	
	@Override
	public String toString() {
		return "LogInDto [email=" + email + ", password=" + password + "]";
	}

	
	
	
}
