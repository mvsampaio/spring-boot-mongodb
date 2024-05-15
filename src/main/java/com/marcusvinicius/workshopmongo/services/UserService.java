package com.marcusvinicius.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marcusvinicius.workshopmongo.domain.User;
import com.marcusvinicius.workshopmongo.dto.UserDTO;
import com.marcusvinicius.workshopmongo.repository.UserRepository;
import com.marcusvinicius.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	private UserRepository repository; 	
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		User obj = findById(id);
		repository.delete(obj);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);		
	}
	
	private void updateData(User newObj, User obj) {		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
