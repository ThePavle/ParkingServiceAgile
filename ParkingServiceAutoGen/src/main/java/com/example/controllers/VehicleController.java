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

import com.example.demo.Vehicle;
import com.example.repository.VehicleRepository;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@GetMapping("vehicle")
	public Collection<Vehicle> getVehicles() {
		return vehicleRepository.findAll();
	}
	
	@GetMapping("vehicle/{id}")
	public Vehicle getVehicleById(@PathVariable("id") Integer id) {
		return vehicleRepository.getOne(id);
	}
	

	@PostMapping("vehicle")
	public ResponseEntity<Vehicle> insertVehicle(@RequestBody Vehicle vehicle){
		if(!vehicleRepository.existsById(vehicle.getId())) {
			vehicleRepository.save(vehicle);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("vehicle")
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle){
		if(!vehicleRepository.existsById(vehicle.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		vehicleRepository.save(vehicle);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("vehicle/{id}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") Integer id){
		if(!vehicleRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		vehicleRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
