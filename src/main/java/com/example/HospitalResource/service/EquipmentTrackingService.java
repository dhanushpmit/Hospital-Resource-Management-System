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
	
	public EquipmentTrackingService(EquipmentTrackingRepository repo) {
		this.repo=repo;
	}
	
	public List<EquipmentTracking> findAll(){
		return repo.findAll();
	}
	
	public Optional<EquipmentTracking> findById(Long id){
		return repo.findById(id);
	}
	

	
	public EquipmentTracking create(EquipmentTrackingDto dto) {
	    EquipmentTracking equipment = new EquipmentTracking();
	    equipment.setEquipCode(dto.getEquipCode());
	    equipment.setEquipName(dto.getEquipName());
	    equipment.setStatus(dto.getStatus());
	    equipment.setAssignedAt(dto.getAssignedAt());
	    equipment.setReleasedAt(dto.getReleasedAt());

	    return repo.save(equipment);
	}

	public EquipmentTracking update(Long id,EquipmentTracking updated) {
		return repo.findById(id)
				.map(existing->{ 
					if(updated.getEquipName()!=null)existing.setEquipName(updated.getEquipName());
					if(updated.getStatus()!=null) existing.setStatus(updated.getStatus());
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
