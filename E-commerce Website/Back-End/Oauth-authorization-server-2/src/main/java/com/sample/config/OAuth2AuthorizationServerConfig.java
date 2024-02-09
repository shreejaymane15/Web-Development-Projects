package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;

import com.sample.repository.OAuthClientDetailsRepository;

@Configuration
public class OAuth2AuthorizationServerConfig{
	
    @Autowired
    private OAuthClientDetailsRepository clientDetailsRepository;

    @Bean
    public OAuth2AuthorizationServerConfiguration oauth2AuthorizationServerConfiguration() {
        OAuth2AuthorizationServerConfiguration config = new OAuth2AuthorizationServerConfiguration();
        return config;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize)->authorize
                		.anyRequest().authenticated()
                  )
                .oauth2ResourceServer(Customizer.withDefaults());
        return http.build();
    }

}
