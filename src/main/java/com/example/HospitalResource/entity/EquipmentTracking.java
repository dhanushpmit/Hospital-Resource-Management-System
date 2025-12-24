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
@Table(name="Equipment_tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentTracking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String equipCode;
	private String equipName;
	private String status;
	private LocalDateTime assignedAt;
	private LocalDateTime releasedAt;
	


}
