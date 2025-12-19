package com.example.HospitalResource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.HospitalResource.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> 
{
List<Patient> findByStatus( String status);

List<Patient> findByStaffList_Id( Long staffId);

List<Patient> findByNameContainingIgnoreCase(String namePart);
}
