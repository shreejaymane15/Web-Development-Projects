package com.indiantrait.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name", length = 20, nullable = false)
	private String firstName;
	
	@Column(name="last_name", length = 20, nullable = false)
	private String lastName;
	
	@Column(name="mobile", length = 10, nullable = false)
	private String mobile;
	
	@Column(name="email", length = 100, nullable = false)
	private String email;
	
	@Column(name="password", length = 255, nullable = false)
	private String password;
	
	@Column(name="image", length = 200)
	private String image;
	
	@Column(name="enabled", columnDefinition = "tinyint(1) default 0", nullable = false)
	private boolean enabled;
	
	@Column(name="role",length = 20, nullable = false)
	@Enumerated(EnumType.STRING) 
	private Role role;
	
	


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public User(long id, String firstName, String lastName, String mobile, String email, String password, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role = role;
	}




	public User(long id, String firstName, String lastName, String mobile, String email, String password, String image,
			boolean enabled, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.image = image;
		this.enabled = enabled;
		this.role = role;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public boolean isEnabled() {
		return enabled;
	}




	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
				+ ", email=" + email + ", password=" + password + ", image=" + image + ", enabled=" + enabled
				+ ", role=" + role + "]";
	}




}
