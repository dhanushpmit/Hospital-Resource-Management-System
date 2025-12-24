package com.example.HospitalResource.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.dto.BedAllocationDto;
import com.example.HospitalResource.entity.Bed;
import com.example.HospitalResource.entity.BedAllocation;
import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.exception.BedAllocationNotFoundException;
import com.example.HospitalResource.exception.InvalidBedAllocationException;
import com.example.HospitalResource.repository.BedAllocationRepository;
import com.example.HospitalResource.repository.BedRepository;
import com.example.HospitalResource.repository.PatientRepository;

@Service
public class BedAllocationService {

    private final BedAllocationRepository allocationRepo;
    private final PatientRepository patientRepo;
    private final BedRepository bedRepo;

    public BedAllocationService(
            BedAllocationRepository allocationRepo,
            PatientRepository patientRepo,
            BedRepository bedRepo) {
        this.allocationRepo = allocationRepo;
        this.patientRepo = patientRepo;
        this.bedRepo = bedRepo;
    }

    // Allocate bed
    public BedAllocation allocate(BedAllocationDto dto) {
    	
    	if(dto.getPatientId()==null) {
    		throw new IllegalArgumentException("patientId must not be null");
    	}
    	
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Bed bed = bedRepo.findById(dto.getBedId())
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (!bed.getIsAvailable()) {
            throw new RuntimeException("Bed already occupied");
        }

        allocationRepo.findByPatientIdAndAllocationEndNull(patient.getId())
                .ifPresent(a -> {
                    throw new RuntimeException("Patient already has an active bed");
                });

        BedAllocation allocation = BedAllocation.builder()
                .patient(patient)
                .bed(bed)
                .allocationStart(dto.getAllocationStart())
                .reason(dto.getReason())
                .build();

        bed.setIsAvailable(false);
        bedRepo.save(bed);

        return allocationRepo.save(allocation);
    }

 // Discharge patient from bed allocation
    public BedAllocation discharge(Long allocationId, LocalDateTime dischargeTime) {

        BedAllocation allocation = allocationRepo.findById(allocationId)
                .orElseThrow(() ->
                        new RuntimeException("Bed allocation not found with id: " + allocationId)
                );

        //cannot discharge twice
        if (allocation.getAllocationEnd() != null) {
            throw new RuntimeException(
                    "This bed allocation is already discharged at " + allocation.getAllocationEnd()
            );
        }

        //discharge time fallback
        LocalDateTime finalDischargeTime =
                (dischargeTime != null) ? dischargeTime : LocalDateTime.now();

        allocation.setAllocationEnd(finalDischargeTime);

        // ❗ VALIDATION 3: release bed safely
        Bed bed = allocation.getBed();
        if (bed != null && Boolean.FALSE.equals(bed.getIsAvailable())) {
            bed.setIsAvailable(true);
            bedRepo.save(bed);
        }

        return allocationRepo.save(allocation);
    }


    public BedAllocation findCurrentBedByPatient(Long patientId) {
        return allocationRepo.findByPatientIdAndAllocationEndNull(patientId)
                .orElseThrow(() -> new RuntimeException("No active bed found"));
    }
    
    public List<BedAllocation> findAll(){
    	return allocationRepo.findAll();
    }
}

