package com.indiantrait.authservice.entity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
	
	USER(Collections.emptySet()),
	
	ADMIN(
			Set.of(
					Permission.ADMIN_READ,
					Permission.ADMIN_CREATE,
					Permission.ADMIN_UPDATE,
					Permission.ADMIN_DELETE
			)
			
	),
	
	MANAGER(
			Set.of(
					Permission.MANAGER_READ,
					Permission.MANAGER_CREATE,
					Permission.MANAGER_UPDATE,
					Permission.MANAGER_DELETE
			)
	);
		

	private final Set<Permission> permissions;

	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	public List<SimpleGrantedAuthority> getAuthorities(){
		var authorities = getPermissions().stream()
										  .map(permission -> new SimpleGrantedAuthority(permission.name()))
										  .toList();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return authorities;
	}
	
	
}
