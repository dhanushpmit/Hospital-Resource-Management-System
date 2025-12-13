package com.example.HospitalResource.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name="patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate dob;
	private String gender;
	private String contact;
	private String status;
	private LocalDateTime admissionDate;
	private LocalDateTime dischargeDate;
	private String medicalHisSummary;
	
	@Column(name="assigned_staffId")
	private Long staffId;
	

}
