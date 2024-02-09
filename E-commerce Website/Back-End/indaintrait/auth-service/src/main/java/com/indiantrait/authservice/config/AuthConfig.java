package com.indiantrait.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.security.MacAlgorithm;

@Configuration
public class AuthConfig{
	
	private static final String[] WHITE_LIST_URLS = {
			"/", 
			"/indiantrait/auth/register", 
			"/indiantrait/auth/login", 
			"/indiantrait/auth/verifyRegistration", 
			"/indiantrait/auth/emailVerifiaction",
			"/indiantrait/auth/hello"
	};

	@Autowired
	private UserDetailsService userDetailsService;
		
	@Autowired
	private JWTRequestFilter jwtRequestFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests((authorize) ->
					authorize
					.requestMatchers(WHITE_LIST_URLS).permitAll()
					.anyRequest().permitAll())
		     .sessionManagement(httpSecuritySessionManagementConfigurer -> 
		      						httpSecuritySessionManagementConfigurer
		      							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider());
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);	
		return http.build();
	}
	
	@Bean			
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
