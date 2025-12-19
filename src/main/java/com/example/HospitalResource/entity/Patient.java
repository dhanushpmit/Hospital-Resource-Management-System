package com.example.HospitalResource.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude= {"staffList","bedAllocations","equipmentTrackings"})
@ToString(exclude= {"staffList","bedAllocations","equipmentTrackings"})
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	
	@ManyToMany(cascade= {CascadeType.MERGE})
	@JoinTable(name="patient_staff",
				joinColumns=@JoinColumn(name="patient_id"),
				inverseJoinColumns = @JoinColumn(name="staff_id"))
	@JsonIgnoreProperties({"patients"})
	@Builder.Default
	private List<Staff> staffList=new ArrayList<>();
	
	@OneToMany(mappedBy="patient",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties({"patient"})
	@Builder.Default
	private List<BedAllocation> bedAllocations=new ArrayList<>();
	
	@OneToMany(mappedBy="patient",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties({"patient"})
	@Builder.Default
	private List<EquipmentTracking> equipmentTrackings=new ArrayList<>();
	
	
	  // ===== BIDIRECTIONAL HELPERS =====

    public void addStaff(Staff staff) {
    	if(staffList==null) {
    		staffList=new ArrayList<>();
    	}
    	if(!this.staffList.contains(staff)) {
            this.staffList.add(staff);
            staff.getPatients().add(this);
        }
    }

    public void removeStaff(Staff staff) {
        this.staffList.remove(staff);
        staff.getPatients().remove(this);
    }

    public void addBedAllocation(BedAllocation bed) {
        bedAllocations.add(bed);
        bed.setPatient(this);
    }

    public void addEquipment(EquipmentTracking equipment) {
        equipmentTrackings.add(equipment);
        equipment.setPatient(this);
    }
}
