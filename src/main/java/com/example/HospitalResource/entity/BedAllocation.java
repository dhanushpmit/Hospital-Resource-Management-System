package com.example.HospitalResource.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	private LocalDateTime allocationStart;
	private LocalDateTime allocationEnd;
	

	@ManyToOne(optional =false)
	@JoinColumn(name="patient_id",nullable=false)
	@JsonIgnoreProperties({"bedAllocations","staffList"})
	private Patient patient;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="bed_id")
	private Bed bed;
	
	private String reason;

	
}
