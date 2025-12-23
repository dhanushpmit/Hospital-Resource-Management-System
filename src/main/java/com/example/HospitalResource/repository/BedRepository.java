package com.example.HospitalResource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HospitalResource.entity.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {

    Optional<Bed> findByBedNumber(String bedNumber);

    List<Bed> findByIsAvailable(Boolean isAvailable);
}
