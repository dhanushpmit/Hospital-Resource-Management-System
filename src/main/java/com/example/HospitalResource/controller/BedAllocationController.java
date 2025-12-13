package com.example.HospitalResource.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalResource.entity.BedAllocation;
import com.example.HospitalResource.service.BedAllocationService;

@RestController
@RequestMapping("/api/beds")
public class BedAllocationController {
	
	private final BedAllocationService service;
	
	public BedAllocationController(BedAllocationService service){
		this.service=service;
	}
	
	@PostMapping
	public ResponseEntity<BedAllocation> create(@RequestBody BedAllocation bed ){
		return ResponseEntity.ok(service.create(bed));
	}
	
	@GetMapping
	public ResponseEntity<List<BedAllocation>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BedAllocation> getById(@PathVariable("id") Long id){
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<BedAllocation>> getByPatient(@PathVariable Long patientId){
		return ResponseEntity.ok(service.findByPatientId(patientId));
	}
}
