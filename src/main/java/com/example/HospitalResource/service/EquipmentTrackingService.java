package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.dto.EquipmentTrackingDto;
import com.example.HospitalResource.entity.EquipmentTracking;
import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.repository.EquipmentTrackingRepository;
import com.example.HospitalResource.repository.PatientRepository;

@Service
public class EquipmentTrackingService {
	
	private final EquipmentTrackingRepository repo;
	private final PatientRepository patientRepo;
	
	public EquipmentTrackingService(EquipmentTrackingRepository repo,PatientRepository patientRepo) {
		this.repo=repo;
		this.patientRepo=patientRepo;
	}
	
	public List<EquipmentTracking> findAll(){
		return repo.findAll();
	}
	
	public Optional<EquipmentTracking> findById(Long id){
		return repo.findById(id);
	}
	

	public List<EquipmentTracking> findByPatientId(Long patientId){
		return repo.findByPatientId(patientId);
	}
	
	public EquipmentTracking create(EquipmentTrackingDto dto) {
		Patient patient = patientRepo.findById(dto.getPatientId())
	            .orElseThrow(() -> 
	                    new RuntimeException("Patient not found with id: " + dto.getPatientId()));

	    EquipmentTracking equipment = new EquipmentTracking();
	    equipment.setEquipCode(dto.getEquipCode());
	    equipment.setEquipName(dto.getEquipName());
	    equipment.setStatus(dto.getStatus());
	    equipment.setAssignedAt(dto.getAssignedAt());
	    equipment.setReleasedAt(dto.getReleasedAt());
	    equipment.setPatient(patient);  // Associate the patient

	    return repo.save(equipment);
	}

	public EquipmentTracking update(Long id,EquipmentTracking updated) {
		return repo.findById(id)
				.map(existing->{ 
					if(updated.getEquipName()!=null)existing.setEquipName(updated.getEquipName());
					if(updated.getStatus()!=null) existing.setStatus(updated.getStatus());
					if(updated.getPatient()!=null) existing.setPatient(updated.getPatient());
					if(updated.getAssignedAt()!=null) existing.setAssignedAt(updated.getAssignedAt());
					if(updated.getReleasedAt()!=null) existing.setReleasedAt(updated.getReleasedAt());
					return repo.save(existing);
				}).orElseThrow(()->new RuntimeException("EquipmentTracking not found with id: "+id));
	}
	public void deleteById(Long id) {
		if(!repo.existsById(id)) {
			throw new RuntimeException("EquipmentTracking not found with id: "+id);
		}
		repo.deleteById(id);
	}
	
	
}
