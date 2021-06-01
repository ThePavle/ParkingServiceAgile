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

import com.example.demo.Fine;
import com.example.repository.FineRepository;

@RestController
public class FineController {

	@Autowired
	private FineRepository fineRepository;
	
	@GetMapping("fine")
	public Collection<Fine> getFines() {
		return fineRepository.findAll();
	}
	
	@GetMapping("fine/{id}")
	public Fine getFineById(@PathVariable("id") Integer id) {
		return fineRepository.getOne(id);
	}
	

	@PostMapping("fine")
	public ResponseEntity<Fine> insertFine(@RequestBody Fine fine){
		if(!fineRepository.existsById(fine.getId())) {
			fineRepository.save(fine);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("fine")
	public ResponseEntity<Fine> updateFine(@RequestBody Fine fine){
		if(!fineRepository.existsById(fine.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		fineRepository.save(fine);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("fine/{id}")
	public ResponseEntity<Fine> deleteFine(@PathVariable("id") Integer id){
		if(!fineRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		fineRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
