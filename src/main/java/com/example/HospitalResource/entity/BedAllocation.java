package com.example.HospitalResource.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Bed_Alloc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BedAllocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long patientId;
	private String ward;
	private String bedNumber;
	private String roomNumber;
	private Boolean isOccupied;
	private LocalDateTime allocationStart;
	private LocalDateTime allocationEnd;
	
	
}
