package com.example.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Controller;
import com.example.repository.ControllerRepository;

@RestController
public class ControllerController {

	@Autowired
	private ControllerRepository controllerRepository;
	
	@GetMapping("controller")
	public Collection<Controller> getControllers() {
		return controllerRepository.findAll();
	}
	
	@GetMapping("controller/{id}")
	public Controller getControllerById(@PathVariable("id") Integer id) {
		return controllerRepository.getOne(id);
	}
	

	@PostMapping("controller")
	public ResponseEntity<Controller> insertController(@RequestBody Controller controller){
		if(!controllerRepository.existsById(controller.getId())) {
			controllerRepository.save(controller);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("controller")
	public ResponseEntity<Controller> updateController(@RequestBody Controller controller){
		if(!controllerRepository.existsById(controller.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		controllerRepository.save(controller);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("controller/{id}")
	public ResponseEntity<Controller> deleteController(@PathVariable("id") Integer id){
		if(!controllerRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		controllerRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
