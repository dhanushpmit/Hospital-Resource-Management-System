package com.example.HospitalResource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HospitalResource.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

	Optional<Staff> findByStaffCode(String staffCode);
	List<Staff> findByRole(String role);
	
}
