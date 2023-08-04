package com.app.keycloak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity

//for role base authentication 
@EnableMethodSecurity 
// boolean prePostEnabled() default true; this by default
/*
 * Determines if Spring Security's {@link PreAuthorize}, {@link PostAuthorize},
 * {@link PreFilter}, and {@link PostFilter} annotations should be enabled.
 * Default is true.
 */


// connecting JwtAuthConverters to securityConfiguration

@RequiredArgsConstructor

public class SecurityConfiguration {

	private final JwtAuthConverters jwtAuthConverters;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated();

		//http.oauth2ResourceServer().jwt(); // jwt token is validated through oauth2Resource
		
		http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverters); // we use our own converter
	

		// session management policy
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}
}
