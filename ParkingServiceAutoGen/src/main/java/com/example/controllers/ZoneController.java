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

import com.example.demo.Zone;
import com.example.repository.ZoneRepository;

@RestController
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;
	
	@GetMapping("zone")
	public Collection<Zone> getZones() {
		return zoneRepository.findAll();
	}
	
	@GetMapping("zone/{id}")
	public Zone getZoneById(@PathVariable("id") Integer id) {
		return zoneRepository.getOne(id);
	}
	

	@PostMapping("zone")
	public ResponseEntity<Zone> insertZone(@RequestBody Zone zone){
		if(!zoneRepository.existsById(zone.getId())) {
			zoneRepository.save(zone);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("zone")
	public ResponseEntity<Zone> updateZone(@RequestBody Zone zone){
		if(!zoneRepository.existsById(zone.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		zoneRepository.save(zone);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("zone/{id}")
	public ResponseEntity<Zone> deleteZone(@PathVariable("id") Integer id){
		if(!zoneRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		zoneRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
