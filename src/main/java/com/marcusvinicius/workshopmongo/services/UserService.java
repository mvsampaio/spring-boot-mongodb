package com.marcusvinicius.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcusvinicius.workshopmongo.domain.User;
import com.marcusvinicius.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository; 	
	
	
	public List<User> findAll() {
		return repository.findAll();
	}
}