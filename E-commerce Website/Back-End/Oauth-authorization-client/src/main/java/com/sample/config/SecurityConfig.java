package com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
					.anyRequest().authenticated())
			.oauth2Login(oauth2Login ->
				oauth2Login.loginPage("/oauth2/authorization/myoauth2"))
			.oauth2Client(Customizer.withDefaults());
		return http.build();
	}
}
