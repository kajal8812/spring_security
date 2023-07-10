package com.learning.spring.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration

public class RoleBasedConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		//	to congigure username and password
		UserDetails userDetails = User.builder().username("Rishi").password(passwordEncoder().encode("mishra"))
				.roles("NORMAL").build();
		UserDetails userDetails1 = User.builder().username("shashi").password(passwordEncoder().encode("kumar"))
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(userDetails, userDetails1);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//to encode passowrd
		return new BCryptPasswordEncoder();
	}

}
