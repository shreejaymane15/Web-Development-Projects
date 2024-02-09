package com.indiantrait.authservice.config;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.indiantrait.authservice.utility.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
			System.out.println(claims);
			
			
			List<GrantedAuthority> authorities = utils.getAuthoritiesFromClaims(claims);
			
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(email, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);		
			filterChain.doFilter( request, response);
		}else {
			System.out.println("Req. did not contain any bearer token");
			filterChain.doFilter( request, response);
		}
		
	}
	

}
