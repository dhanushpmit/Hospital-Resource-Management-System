package com.example.HospitalResource.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude= {"patients"})
@ToString(exclude= {"patients"})
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
	
	@ManyToMany(mappedBy="staffList")
	@JsonIgnoreProperties({"staffList","bedAllocations","equipmentTrackings"})
	private List<Patient> patients=new ArrayList<>();

}
