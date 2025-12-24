package com.example.HospitalResource.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalResource.dto.EquipmentTrackingDto;
import com.example.HospitalResource.entity.EquipmentTracking;
import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.service.EquipmentTrackingService;

@RestController
@RequestMapping("/api/equipment-tracking")
public class EquipmentTrackingController {

	private final EquipmentTrackingService service;
	
	public EquipmentTrackingController(EquipmentTrackingService service) {
		this.service=service;
	}
	
	@GetMapping
	public ResponseEntity<List<EquipmentTracking>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EquipmentTracking> findById(@PathVariable Long id){
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<EquipmentTracking> create(@RequestBody EquipmentTrackingDto equipment){
		EquipmentTracking createdEquip=service.create(equipment);
		return new ResponseEntity<>(createdEquip,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EquipmentTracking> update(@PathVariable Long id,@RequestBody EquipmentTracking equipment){
		try {
			EquipmentTracking updated=service.update(id, equipment);
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
