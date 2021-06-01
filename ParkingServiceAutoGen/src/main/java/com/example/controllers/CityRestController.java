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

import com.example.demo.City;
import com.example.repository.CityRepository;

@RestController
public class CityRestController {
	
	@Autowired
	private CityRepository cityRepository;
	
	@GetMapping("city")
	public Collection<City> getCities() {
		return cityRepository.findAll();
	}
	
	@GetMapping("city/{id}")
	public City getCitiesById(@PathVariable("id") Integer id) {
		return cityRepository.getOne(id);
	}
	

	@PostMapping("city")
	public ResponseEntity<City> insertCity(@RequestBody City city){
		if(!cityRepository.existsById(city.getId())) {
			cityRepository.save(city);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("city")
	public ResponseEntity<City> updateCity(@RequestBody City city){
		if(!cityRepository.existsById(city.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		cityRepository.save(city);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("city/{id}")
	public ResponseEntity<City> deleteCity(@PathVariable("id") Integer id){
		if(!cityRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		cityRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
