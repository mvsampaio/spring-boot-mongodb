package com.marcusvinicius.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marcusvinicius.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
