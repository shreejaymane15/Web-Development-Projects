package com.indiantrait.apigateway.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;
import com.indiantrait.apigateway.utilities.JwtUtils;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	
		private final WebClient.Builder webClientBuilder;
			
		@Autowired
		private RouteValidator routeValidator;
		
//		@Autowired
//		private RestTemplate template;
		
		@Autowired
		private JwtUtils utils;
	
		public AuthenticationFilter(WebClient.Builder webClientBuilder) {
			super(Config.class);
			this.webClientBuilder = webClientBuilder;
		}
		

		
		@Override
		public GatewayFilter apply(Config config) {
			return ((exchange, chain)->{
				if(routeValidator.isSecured.test(exchange.getRequest())){
					//header contains token or not
					if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
						throw new RuntimeException("Missing Authorization Header");
					}
					
					String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
					
					if(authHeader != null && authHeader.startsWith("Bearer ")) {
						authHeader = authHeader.substring(7);
						
					}
					try {
						utils.validateJwtToken(authHeader);
					}catch(Exception e) {
						System.out.println("Invalid Access");
						throw new RuntimeException("Unauthorized Access To Application");
					}
				}
	    		return chain.filter(exchange.mutate().build());
			});
		}
		
		

	
		public static class Config{
			
		}
	
}
