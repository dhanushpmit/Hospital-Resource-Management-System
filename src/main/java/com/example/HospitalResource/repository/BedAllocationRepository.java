package com.example.HospitalResource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HospitalResource.entity.BedAllocation;

public interface BedAllocationRepository extends JpaRepository<BedAllocation, Long> {

	
	Optional<BedAllocation> findByPatientIdAndAllocationEndNull(Long patientId);

	Optional<BedAllocation> findByBedIdAndAllocationEndIsNull(Long bedId);
	
	List<BedAllocation> findByPatientId(Long patientId);
}
