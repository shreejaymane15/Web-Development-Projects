package com.cms.Models;

public enum Permission {
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:read");
	
	private final String permission;
	
	Permission(String permission){
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}

}
