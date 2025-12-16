package com.example.HospitalResource.controller;

import java.util.List;

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
import org.springframework.web.service.annotation.PutExchange;

import com.example.HospitalResource.entity.Staff;
import com.example.HospitalResource.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
	private final StaffService service;
	
	public StaffController(StaffService service) {
		this.service=service;
	}
	
	@GetMapping
	public ResponseEntity<List<Staff>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Staff> getById(@PathVariable Long id){
		return service.findById(id)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/code/{staffCode}")
	public ResponseEntity<Staff> getByCode(@PathVariable String staffCode){
		return service.findByStaffCode(staffCode)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/department/{department}")
	public ResponseEntity<List<Staff>> getByDepartment(@PathVariable String department){
		return ResponseEntity.ok(service.findByDepartment(department));
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<Staff>> getByRole(@PathVariable String role){
		return ResponseEntity.ok(service.findByRole(role));
	}
	
	@GetMapping("/active/{isActive}")
	public ResponseEntity<List<Staff>> getByActive(@PathVariable Boolean isActive){
		return ResponseEntity.ok(service.findByActive(isActive));
	}
	
	@GetMapping("/shift/{shift}")
	public ResponseEntity<List<Staff>> getByShift(@PathVariable String shift){
		return ResponseEntity.ok(service.findByShift(shift));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Staff>> searchByName(@RequestParam String q){
		return ResponseEntity.ok(service.searchByName(q));
	}
	
	@PostMapping
	public ResponseEntity<Staff> create(@RequestBody Staff staff){
		return ResponseEntity.ok(service.create(staff));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Staff> update(@PathVariable Long id,@RequestBody Staff staff){
		try {
			Staff updated=service.update(id,staff);
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
