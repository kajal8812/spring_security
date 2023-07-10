package com.learning.spring.SpringSecurity.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@EnableMethodSecurity(jsr250Enabled = true)

// @EnableMethodSecurity  to have method level security using@preauthorize and @postauthorize(most prferred ones)
public class HomeController {

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	//before entering into this method the role gets validated
	@RolesAllowed({ "ADMIN", "NORMAL" })
	public String getUsers() {
		return "users";
	}

	@GetMapping("/currentuser")
	public String getUsers(Principal principal) {
		return principal.getName();
	}
}
