package com.example.HospitalResource.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.exception.PatientNotFoundException;
import com.example.HospitalResource.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	private final PatientService service;
	
	public PatientController(PatientService service) {
		this.service=service;
	}
	
	@GetMapping
	public ResponseEntity<List<Patient>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getById(@PathVariable Long id) {
	    return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/staff/{staffId}")
	public ResponseEntity<List<Patient>> getByStaff(@PathVariable Long staffId){
		return ResponseEntity.ok(service.findByStaffId(staffId));
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Patient>> getByStatus(@PathVariable String status){
		return ResponseEntity.ok(service.findByStatus(status));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Patient>> searchByName(@RequestParam String q){
		return ResponseEntity.ok(service.findByName(q));
	}
	
	
	@PostMapping
	public ResponseEntity<Patient> create(@RequestBody Patient patient,@RequestParam(required=false) Long staffId){
		return ResponseEntity.ok(service.create(patient,staffId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> update(@PathVariable Long id,@RequestBody Patient patient){
		try {
			Patient updated=service.update(id, patient);
			return ResponseEntity.ok(updated);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
			
		}
	}
	
//	@PatchMapping("/{id}")
//	public ResponseEntity<Patient> patch(@PathVariable Long id,@RequestBody Patient patch){
//		try {
//			Patient updated=service.partialUpdate(id, patch);
//			return ResponseEntity.ok(updated);
//		}catch(RuntimeException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
	
	
	@PutMapping("/{id}/discharge")
	public ResponseEntity<Patient> dischargePatient(@PathVariable Long id,@RequestParam(required = false) LocalDateTime time) {
	    return ResponseEntity.ok(service.dischargePatient(id, time));
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//assign staff
	@PostMapping("/{patientId}/assign-staff/{staffId}")
    public ResponseEntity<Patient> assignStaff(
            @PathVariable Long patientId,
            @PathVariable Long staffId) {
        return ResponseEntity.ok(service.assignStaff(patientId, staffId));
    }
	
	
	 @PostMapping("/{patientId}/remove-staff/{staffId}")
	    public ResponseEntity<Patient> removeStaff(@PathVariable Long patientId, @PathVariable Long staffId) {
	        return ResponseEntity.ok(service.removeStaff(patientId, staffId));
	    }
	
}
