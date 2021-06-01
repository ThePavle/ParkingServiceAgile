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

import com.example.demo.Payment;
import com.example.repository.PaymentRepository;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@GetMapping("payment")
	public Collection<Payment> getPayments() {
		return paymentRepository.findAll();
	}
	
	@GetMapping("payment/{id}")
	public Payment getPaymentById(@PathVariable("id") Integer id) {
		return paymentRepository.getOne(id);
	}
	

	@PostMapping("payment")
	public ResponseEntity<Payment> insertPayment(@RequestBody Payment payment){
		if(!paymentRepository.existsById(payment.getId())) {
			paymentRepository.save(payment);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@PutMapping("payment")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment){
		if(!paymentRepository.existsById(payment.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		paymentRepository.save(payment);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("payment/{id}")
	public ResponseEntity<Payment> deletePayment(@PathVariable("id") Integer id){
		if(!paymentRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
		paymentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
