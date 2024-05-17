package com.marcusvinicius.workshopmongo.services;

import java.util.Date;
import java.util.List;
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
	
	public List<Post> findByTitle(String text) {
		return repository.searchTitle(text);
		//return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {	
		//Tratamento para considerar o final do dia seguinte		
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);		
		return repository.fullSearch(text, minDate, maxDate);
	}
}
