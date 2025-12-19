package com.example.HospitalResource.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalResource.dto.BedAllocationDto;
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
	public ResponseEntity<BedAllocation> create(@RequestBody BedAllocationDto bed ){
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
	@PutMapping("/{id}")
	public ResponseEntity<BedAllocation> update(@PathVariable Long id,@RequestBody BedAllocation bed){
		try {
			BedAllocation updated=service.update(id, bed);
			return ResponseEntity.ok(updated);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
