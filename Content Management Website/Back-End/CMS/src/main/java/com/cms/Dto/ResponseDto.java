package com.cms.Dto;

public class ResponseDto {
	
	private String message;
	
	private int status;
	
	private Long user_id;
	
	private String token;

	public ResponseDto() {
		super();
	}

	public ResponseDto(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}
	

	public ResponseDto(String message, int status, Long user_id, String token) {
		super();
		this.message = message;
		this.status = status;
		this.user_id = user_id;
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	

}
