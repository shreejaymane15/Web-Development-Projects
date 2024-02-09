package com.indiantrait.apigateway.utilities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {
	
	@Value("${SECRET_KEY}")
	private String jwtSecretkey;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	private SecretKey key;
	
	
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecretkey.getBytes());
	}
	
		

	
	public String getUserNameFromJwtToken(Claims claims) {
		return claims.getSubject();
	}
	
	
	public Claims validateJwtToken(String JwtToken) {
		
		Claims claims = Jwts.parser()
							.verifyWith(key)
							.build()
							.parseSignedClaims(JwtToken)
							.getPayload();
		
		return claims;
	}
	
	
}
