package com.cms.Utility;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.cms.security.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	@Value("${SECRET_KEY}")
	private String jwtSecretkey;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	
	private Key key;
	
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecretkey.getBytes());		
	}
	
	
	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
		return Jwts.builder()
				   .setSubject(userPrincipal.getUsername())
				   .setIssuedAt(new Date())
				   .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				   .claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				   .signWith(key, SignatureAlgorithm.HS512)
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
		
		Claims claims = Jwts.parserBuilder()
							.setSigningKey(key).build()
							.parseClaimsJws(JwtToken).getBody();
		
		return claims;
	}
	
	
	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims){
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
}
