package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.entity.BedAllocation;
import com.example.HospitalResource.repository.BedAllocationRepository;

@Service
public class BedAllocationService {
	
	private final BedAllocationRepository bedAllocationRepository;
	
	public BedAllocationService(BedAllocationRepository bedAllocationRepository) {
		this.bedAllocationRepository=bedAllocationRepository;
	}
	
	public List<BedAllocation> findAll(){
		return bedAllocationRepository.findAll();
	}

	public Optional<BedAllocation> findById(Long id){
		return bedAllocationRepository.findById(id);
	}
	
	public BedAllocation create(BedAllocation bedAllocation) {
		return bedAllocationRepository.save(bedAllocation);
	}
	
	public BedAllocation update(Long id,BedAllocation updated) {
		return bedAllocationRepository.findById(id)
				.map(existing ->{
					existing.setPatientId(updated.getPatientId());
						existing.setWard(updated.getWard());
					existing.setBedNumber(updated.getBedNumber());
					existing.setRoomNumber(updated.getRoomNumber());
					existing.setIsOccupied(updated.getIsOccupied());
					existing.setAllocationEnd(updated.getAllocationStart());
					existing.setAllocationEnd(updated.getAllocationEnd());
						return bedAllocationRepository.save(existing);
				}).orElseThrow(()-> new RuntimeException("BedAllocation not found with id: "+id));
	}
	public List<BedAllocation> findByPatientId(Long patientId){
		return bedAllocationRepository.findByPatientId(patientId);
	}
}
