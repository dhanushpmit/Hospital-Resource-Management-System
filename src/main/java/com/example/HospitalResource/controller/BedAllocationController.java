package com.example.HospitalResource.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalResource.dto.BedAllocationDto;
import com.example.HospitalResource.entity.BedAllocation;
import com.example.HospitalResource.service.BedAllocationService;

@RestController
@RequestMapping("api/bed-alloc")
public class BedAllocationController {
	
	private final BedAllocationService service;
	
	public BedAllocationController(BedAllocationService service){
		this.service=service;
	}
	
	@PostMapping
	public BedAllocation allocate(@RequestBody BedAllocationDto dto) {
		return service.allocate(dto);
	}
	
	@PutMapping("/{id}/discharge")
	public BedAllocation discharge(@PathVariable Long id,@RequestBody BedAllocation body) {
		return service.discharge(id,body.getAllocationEnd() );
	}
	
	@GetMapping
	public List<BedAllocation> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/patient/{patientId}/current")
	public BedAllocation current(@PathVariable Long patientId) {
		return service.findCurrentBedByPatient(patientId);
	}
}
