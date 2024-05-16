package com.marcusvinicius.workshopmongo.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcusvinicius.workshopmongo.domain.Post;
import com.marcusvinicius.workshopmongo.services.PostService;


@RestController
@RequestMapping("/posts")
public class PostResource {

	private PostService service;
	
	public PostResource(PostService userService) {
		this.service = userService;
	}
	
	@GetMapping("{id}")
 	public ResponseEntity<Post> findById(@PathVariable("id") String id) {	
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}	
}
