package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.entity.Staff;
import com.example.HospitalResource.exception.PatientNotFoundException;
import com.example.HospitalResource.repository.PatientRepository;
import com.example.HospitalResource.repository.StaffRepository;

@Service
public class PatientService {

	private final PatientRepository patientRepo;
	private final StaffRepository staffRepo;

	
	public PatientService(PatientRepository patientRepo,StaffRepository staffRepo) {
		this.patientRepo=patientRepo;
		this.staffRepo=staffRepo;

	}
	
	public List<Patient> findAll(){
		return patientRepo.findAll();
	}
	
	public Patient findById(Long id){
		return patientRepo.findById(id)
				.orElseThrow(()->new PatientNotFoundException("Patient not found with id: " + id));
	}
	public Patient create(Patient patient,Long staffId) {
		if(staffId!=null) {
			Staff staff=staffRepo.findById(staffId)
					.orElseThrow(()-> new RuntimeException("Staff not found with the id: "+staffId ));
			patient.addStaff(staff);
		}
		return patientRepo.save(patient);
	}
	
	public Patient update(Long id,Patient pat) {
		return patientRepo.findById(id)
				.map(existing->{
					if(pat.getName()!=null) existing.setName(pat.getName());
					if(pat.getDob()!=null) existing.setDob(pat.getDob());
					if(pat.getGender()!=null) existing.setGender(pat.getGender());
					if(pat.getContact()!=null) existing.setContact(pat.getContact());
					if(pat.getStatus()!=null) existing.setStatus(pat.getStatus());
					if(pat.getAdmissionDate()!=null) existing.setAdmissionDate(pat.getAdmissionDate());
					if(pat.getDischargeDate()!=null) existing.setDischargeDate(pat.getDischargeDate());
					if(pat.getMedicalHisSummary()!=null) existing.setMedicalHisSummary(pat.getMedicalHisSummary());
                    if(pat.getStaffList()!=null) existing.setStaffList(pat.getStaffList());
                    
                    return patientRepo.save(existing);

				}).orElseThrow(()-> new RuntimeException("Patient not found with id: "+id));
	}
	
	public void deleteById(Long id) {
		if(!patientRepo.existsById(id)) {
			throw new RuntimeException("Patient not found with id: "+id);
		}
		patientRepo.deleteById(id);
	}
	
	public List<Patient> findByStaffId(Long staffId){
		return patientRepo.findByStaffList_Id(staffId);
	}
	
	public List<Patient> findByStatus(String status){
		return patientRepo.findByStatus(status);
	}
	
	public List<Patient> findByName(String namePart){
		return patientRepo.findByNameContainingIgnoreCase(namePart);
	}
	
	//patch
	public Patient partialUpdate(Long id,Patient patch) {
		return update(id ,patch);
	}
	
	//Staff Assignment
	
	public Patient assignStaff(Long patientId, Long staffId) {
        Patient patient = findById(patientId);

        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));

        patient.addStaff(staff); // bidirectional helper
        return patientRepo.save(patient);
    }
	
	public Patient removeStaff(Long patientId, Long staffId) {
        Patient patient = findById(patientId);

        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));

        patient.removeStaff(staff);
        return patientRepo.save(patient);
    }
	
	
	
}
 