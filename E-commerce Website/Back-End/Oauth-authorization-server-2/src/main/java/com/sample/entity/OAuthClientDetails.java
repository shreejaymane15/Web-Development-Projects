package com.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OAuthClientDetails {
    
	@Id
    private String clientId;
	
	@Column(columnDefinition = "varchar(255)", nullable = false)
    private String clientSecret;

	@Column(columnDefinition = "varchar(255)", nullable = false)
    private String scope;

	@Column(columnDefinition = "varchar(255)", nullable = false)
    private String authorizedGrantTypes;

	@Column(columnDefinition = "varchar(255)", nullable = false)
    private String webServerRedirectUri;

	@Column(columnDefinition = "varchar(255)", nullable = false)
    private String authorities;

	public OAuthClientDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OAuthClientDetails(String clientId, String clientSecret, String scope, String authorizedGrantTypes,
			String webServerRedirectUri, String authorities) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.scope = scope;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.webServerRedirectUri = webServerRedirectUri;
		this.authorities = authorities;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	
}
