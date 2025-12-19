package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.dto.BedAllocationDto;
import com.example.HospitalResource.entity.BedAllocation;
import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.exception.BedAllocationNotFoundException;
import com.example.HospitalResource.exception.InvalidBedAllocationException;
import com.example.HospitalResource.repository.BedAllocationRepository;
import com.example.HospitalResource.repository.PatientRepository;

@Service
public class BedAllocationService {
	
	private final BedAllocationRepository bedAllocationRepository;
	private final PatientRepository patientRepo;
	
	public BedAllocationService(BedAllocationRepository bedAllocationRepository,PatientRepository patientRepo) {
		this.bedAllocationRepository=bedAllocationRepository;
		this.patientRepo=patientRepo;
	}
	
	public List<BedAllocation> findAll(){
		return bedAllocationRepository.findAll();
	}

	public Optional<BedAllocation> findById(Long id){
		return bedAllocationRepository.findById(id);
	}
	
	public BedAllocation create(BedAllocationDto bedAllocationDto) {
		Patient patient=patientRepo.findById(bedAllocationDto.getPatientId())
				.orElseThrow(()-> new RuntimeException("Patient not found with id: " + bedAllocationDto.getPatientId()));
		BedAllocation bed=new BedAllocation();
		bed.setPatient(patient);
		bed.setWard(bedAllocationDto.getWard());
		bed.setBedNumber(bedAllocationDto.getBedNumber());
		bed.setRoomNumber(bedAllocationDto.getRoomNumber());
		bed.setIsOccupied(bedAllocationDto.getIsOccupied());
		bed.setAllocationStart(bedAllocationDto.getAllocationStart());
        bed.setAllocationEnd(bedAllocationDto.getAllocationEnd());
		
		return bedAllocationRepository.save(bed);
	}
	
	public BedAllocation update(Long id,BedAllocation updated) {
		return bedAllocationRepository.findById(id)
				.map(existing ->{
					existing.setPatient(updated.getPatient());
						existing.setWard(updated.getWard());
					existing.setBedNumber(updated.getBedNumber());
					existing.setRoomNumber(updated.getRoomNumber());
					existing.setIsOccupied(updated.getIsOccupied());
					existing.setAllocationStart(updated.getAllocationStart());
					existing.setAllocationEnd(updated.getAllocationEnd());
						return bedAllocationRepository.save(existing);
				}).orElseThrow(()-> new RuntimeException("BedAllocation not found with id: "+id));
	}
	public List<BedAllocation> findByPatientId(Long patientId){
		return bedAllocationRepository.findByPatientId(patientId);
	}
	public void deleteById(Long id) {
		if(!bedAllocationRepository.existsById(id)) {
			throw new BedAllocationNotFoundException("Bed allocation not found in that id:  "+id);
		}
		bedAllocationRepository.deleteById(id);
	}
}
