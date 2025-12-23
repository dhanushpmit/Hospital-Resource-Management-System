package com.example.HospitalResource.entity;

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
@Table(name = "bed")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bed {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable =false)
	private String bedNumber;
	
	private String ward;
	private String roomNumber;
	
	
	private Boolean isAvailable;

}
