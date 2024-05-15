package com.marcusvinicius.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcusvinicius.workshopmongo.domain.User;
import com.marcusvinicius.workshopmongo.dto.UserDTO;
import com.marcusvinicius.workshopmongo.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class UserResource {

	private UserService service;
	
	public UserResource(UserService userService) {
		this.service = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream()
				.map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}		
	
	@GetMapping("{id}")
 	public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {	
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		//Buscar o endereço do novo objeto e retornar uma resposta com a sua URI
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
					
		return ResponseEntity.noContent().build();
	}
}
