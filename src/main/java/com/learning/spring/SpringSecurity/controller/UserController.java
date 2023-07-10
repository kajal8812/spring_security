package com.learning.spring.SpringSecurity.controller;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.SpringSecurity.models.User;
import com.learning.spring.SpringSecurity.services.UserService;

@RestController
public class UserController {

	//@Autowired
	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@GetMapping("/gettingUsers")

	public List<User> getAllUsers() {
		return service.get();
	}

	@GetMapping("/gettingUserBasedOnName/{name}")
	public User getUserBasedOnName(@PathVariable String name) {
		return service.getBasedOnUsername(name);
	}

	@PostMapping("/addingUser")
	public User getUserBasedOnName(@RequestBody User details) {
		return service.addUser(details);
	}

}
