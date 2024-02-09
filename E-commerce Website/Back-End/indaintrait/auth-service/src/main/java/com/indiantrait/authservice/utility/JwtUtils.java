package com.indiantrait.authservice.utility;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.indiantrait.authservice.entity.CustomUserDetails;

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
	
	
	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
		return Jwts.builder()
				   .claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				   .issuer("http://www.indiantrait.com")
				   .subject(userPrincipal.getUsername())
				   .issuedAt(new Date())
				   .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				   .signWith(key)
				   .compact();
	}
		
	
	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
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
	
	
	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims){
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
}
