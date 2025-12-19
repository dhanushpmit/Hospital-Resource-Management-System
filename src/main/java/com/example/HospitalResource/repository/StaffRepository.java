package com.example.HospitalResource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HospitalResource.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	
	List<Staff> findByRole(String role);
	
	List<Staff> findByDepartment(String department);
	
	List<Staff> findByIsActive(Boolean isActive);
	
	List<Staff> findByShift(String shift);
	
	List<Staff> findByNameContainingIgnoreCase(String namePart);
	
}
