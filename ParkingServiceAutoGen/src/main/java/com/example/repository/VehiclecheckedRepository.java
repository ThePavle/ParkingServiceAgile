package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Vehiclechecked;
import com.example.demo.VehiclecheckedPK;

public interface VehiclecheckedRepository extends JpaRepository<Vehiclechecked,Integer> {

	boolean existsById(VehiclecheckedPK id);

}