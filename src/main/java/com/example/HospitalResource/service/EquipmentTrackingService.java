package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.entity.EquipmentTracking;
import com.example.HospitalResource.repository.EquipmentTrackingRepository;

@Service
public class EquipmentTrackingService {
	
	private final EquipmentTrackingRepository repo;
	
	public EquipmentTrackingService(EquipmentTrackingRepository repo) {
		this.repo=repo;
	}
	
	public List<EquipmentTracking> findAll(){
		return repo.findAll();
	}
	
	public Optional<EquipmentTracking> findById(Long id){
		return repo.findById(id);
	}
	
	public EquipmentTracking create(EquipmentTracking equip) {
		return repo.save(equip);
	}
	public EquipmentTracking update(Long id,EquipmentTracking updated) {
		return repo.findById(id)
				.map(existing->{
					existing.setEquipCode(updated.getEquipCode());
					existing.setEquipName(updated.getEquipName());
					existing.setStatus(updated.getStatus());
					existing.setAssignedToPatientId(updated.getAssignedToPatientId());
					existing.setAssignedAt(updated.getAssignedAt());
					existing.setReleasedAt(updated.getReleasedAt());
					return repo.save(existing);
				}).orElseThrow(()->new RuntimeException("EquipmentTracking not found with id: "+id));
	}
	public void deleteById(Long id) {
		if(!repo.existsById(id)) {
			throw new RuntimeException("EquipmentTracking not found with id: "+id);
		}
		repo.deleteById(id);
	}
	
	public List<EquipmentTracking> findByAssignedToPatientId(String patientId){
		return repo.findByAssignedToPatientId(patientId);
	}
	
}
