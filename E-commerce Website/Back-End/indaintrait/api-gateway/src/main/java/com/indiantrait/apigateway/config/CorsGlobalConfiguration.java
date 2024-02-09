package com.indiantrait.apigateway.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CorsGlobalConfiguration implements WebFluxConfigurer {


	    @Bean
	    public CorsWebFilter corsWebFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfiguration());
	        return new CorsWebFilter(source);
	    }
	    
	    
	    @Bean
	    public CorsConfiguration corsConfiguration() {
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.addAllowedOriginPattern("*");  // You may want to configure this based on your needs
	        corsConfig.addAllowedMethod("*");
	        corsConfig.addAllowedHeader("*");
	        corsConfig.setAllowCredentials(true);

	        return corsConfig;
	    }
    	

    // Other configurations and beans can be added here...

}
