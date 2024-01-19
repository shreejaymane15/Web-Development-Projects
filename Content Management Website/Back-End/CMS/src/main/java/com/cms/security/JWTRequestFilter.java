package com.cms.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cms.Utility.JwtUtils;

import io.jsonwebtoken.Claims;


@Configuration
public class JWTRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils utils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			String token = authHeader.substring(7);
			System.out.println(token);
			Claims claims = utils.validateJwtToken(token);
			
			String email = utils.getUserNameFromJwtToken(claims);
			
			List<GrantedAuthority> authorities = utils.getAuthoritiesFromClaims(claims);
			
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(email, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);		
		}else {
			System.out.println("Req. did not contain any bearer token");
			filterChain.doFilter( request, response);
		}
		
	}
		
}
