package com.indiantrait.entityservice.entity;

public enum Permission {

	ADMIN_READ("admin:read"),
	ADMIN_UPDATE("admin:read"),
	ADMIN_CREATE("admin:read"),
	ADMIN_DELETE("admin:read"),
	MANAGER_READ("management:read"),
	MANAGER_UPDATE("management:read"),
	MANAGER_CREATE("management:read"),
	MANAGER_DELETE("management:read");
	

	private final String permission;
	
	
	private Permission(String permission) {
		this.permission = permission;
	}


	public String getPermission() {
		return permission;
	}
	
	
	
}
