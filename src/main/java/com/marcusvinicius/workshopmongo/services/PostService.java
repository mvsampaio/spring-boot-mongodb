package com.marcusvinicius.workshopmongo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marcusvinicius.workshopmongo.domain.Post;
import com.marcusvinicius.workshopmongo.repository.PostRepository;
import com.marcusvinicius.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	private PostRepository repository; 	
	
	public PostService(PostRepository repository) {
		this.repository = repository;
	}	
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
}
