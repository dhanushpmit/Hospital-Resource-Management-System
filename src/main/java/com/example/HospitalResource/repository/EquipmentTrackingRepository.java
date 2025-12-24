package com.example.HospitalResource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HospitalResource.entity.EquipmentTracking;
import com.example.HospitalResource.entity.Patient;

public interface EquipmentTrackingRepository extends JpaRepository<EquipmentTracking, Long> {
}
