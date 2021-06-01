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

import com.example.demo.Vehiclechecked;
import com.example.repository.VehiclecheckedRepository;

@RestController
public class VehicleCheckedController {
	
	@Autowired
	private VehiclecheckedRepository vehiclecheckedRepository;
	
	@GetMapping("vehicle-checked")
	public Collection<Vehiclechecked> getVehiclecheckeds() {
		return vehiclecheckedRepository.findAll();
	}
	
	@GetMapping("vehicle-checked/{id}")
	public Vehiclechecked getVehiclecheckedById(@PathVariable("id") Integer id) {
		return vehiclecheckedRepository.getOne(id);
	}
	

	@PostMapping("vehicle-checked")
	public ResponseEntity<Vehiclechecked> insertVehiclechecked(@RequestBody Vehiclechecked vehiclechecked){
		if(!vehiclecheckedRepository.existsById(vehiclechecked.getId())) {
			vehiclecheckedRepository.save(vehiclechecked);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("vehicle-checked")
	public ResponseEntity<Vehiclechecked> updateVehiclechecked(@RequestBody Vehiclechecked vehiclechecked){
		if(!vehiclecheckedRepository.existsById(vehiclechecked.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		vehiclecheckedRepository.save(vehiclechecked);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("vehicle-checked/{id}")
	public ResponseEntity<Vehiclechecked> deleteVehiclechecked(@PathVariable("id") Integer id){
		if(!vehiclecheckedRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		vehiclecheckedRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
