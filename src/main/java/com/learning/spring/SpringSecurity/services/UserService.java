package com.learning.spring.SpringSecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.spring.SpringSecurity.models.User;

@Service
public class UserService {

	List<User> list = new ArrayList();

	public UserService() {

		list.add(new User("Allen", "all", "a@a.co", "UK"));
		list.add(new User("Smith", "smi", "s@s.co", "India"));
		//list.add(new User("Smith","smi","s@s.co","India"));

	}

	//get all users
	public List<User> get() {
		return this.list;

	}

	//get details based on username

	public User getBasedOnUsername(String name) {
		//return this.list.stream().filter((User) ->User.getName().equals(name)).findAny().get();
		return this.list.stream().filter((User) -> User.getName().equals(name)).findAny().orElse(null);
	}

	//add user to list
	public User addUser(User u) {
		list.add(u);
		return u;
	}

}
