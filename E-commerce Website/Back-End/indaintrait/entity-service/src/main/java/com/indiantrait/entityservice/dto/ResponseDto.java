package com.indiantrait.entityservice.dto;

public class ResponseDto {

	private String message;
	
	private int status;
	
	private Long userId;
	
	private String token;
	
	
	

	public ResponseDto() {
		super();
	}

	public ResponseDto(String message, int status, Long userId, String token) {
		super();
		this.message = message;
		this.status = status;
		this.userId = userId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", status=" + status + ", userId=" + userId + ", token=" + token
				+ "]";
	}	
	
	
}
