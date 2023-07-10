package com.learning.spring.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//this is for basic authentication cant has option for logout 
		http.authorizeHttpRequests((authz) -> authz.requestMatchers("/gettingUsers").hasRole("NORMAL")

				// this url should only be accessible for normal role assigned users
				.requestMatchers("/currentuser").hasRole("ADMIN").anyRequest().authenticated());
		//http.formLogin();
		http.httpBasic();
		http.csrf().disable();
		return http.build();
	}

}
