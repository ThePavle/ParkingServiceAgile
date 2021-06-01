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

import com.example.demo.Parkinglot;
import com.example.repository.ParkinglotRepository;

@RestController
public class ParkingLotController {
	
	@Autowired
	private ParkinglotRepository parkingLotRepository;
	
	@GetMapping("parking-lot")
	public Collection<Parkinglot> getParkinglots() {
		return parkingLotRepository.findAll();
	}
	
	@GetMapping("parking-lot/{id}")
	public Parkinglot getParkinglotById(@PathVariable("id") Integer id) {
		return parkingLotRepository.getOne(id);
	}
	

	@PostMapping("parking-lot")
	public ResponseEntity<Parkinglot> insertParkinglot(@RequestBody Parkinglot parkinglot){
		if(!parkingLotRepository.existsById(parkinglot.getId())) {
			parkingLotRepository.save(parkinglot);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("parking-lot")
	public ResponseEntity<Parkinglot> updateParkinglot(@RequestBody Parkinglot parkinglot){
		if(!parkingLotRepository.existsById(parkinglot.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		parkingLotRepository.save(parkinglot);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("parking-lot/{id}")
	public ResponseEntity<Parkinglot> deleteParkinglot(@PathVariable("id") Integer id){
		if(!parkingLotRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		parkingLotRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
