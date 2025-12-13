package com.example.HospitalResource.entity;

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
@Table(name="staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String staffCode;
	private String name;
	private String role;
	private String department;
	private String phone;
	private Boolean isActive;
	private String shift;

}
