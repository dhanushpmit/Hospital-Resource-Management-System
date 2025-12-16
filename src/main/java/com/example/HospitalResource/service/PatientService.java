package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.entity.Patient;
import com.example.HospitalResource.repository.PatientRepository;

@Service
public class PatientService {

	private final PatientRepository repo;
	
	public PatientService(PatientRepository repo) {
		this.repo=repo;
	}
	
	public List<Patient> findAll(){
		return repo.findAll();
	}
	
	public Optional<Patient> findById(Long id){
		return repo.findById(id);
	}
	public Patient create(Patient patient) {
		return repo.save(patient);
	}
	
	public Patient update(Long id,Patient pat) {
		return repo.findById(id)
				.map(existing->{
					if(pat.getName()!=null) existing.setName(pat.getName());
					if(pat.getDob()!=null) existing.setDob(pat.getDob());
					if(pat.getGender()!=null) existing.setGender(pat.getGender());
					if(pat.getContact()!=null) existing.setContact(pat.getStatus());
					if(pat.getStatus()!=null) existing.setStatus(pat.getStatus());
					if(pat.getAdmissionDate()!=null) existing.setAdmissionDate(pat.getAdmissionDate());
					if(pat.getDischargeDate()!=null) existing.setDischargeDate(pat.getDischargeDate());
					if(pat.getMedicalHisSummary()!=null) existing.setMedicalHisSummary(pat.getMedicalHisSummary());
                    if(pat.getStaffId()!=null) existing.setStaffId(pat.getStaffId());
                    
                    return repo.save(existing);

				}).orElseThrow(()-> new RuntimeException("Patient not found with id: "+id));
	}
	
	public void deleteById(Long id) {
		if(!repo.existsById(id)) {
			throw new RuntimeException("Patient not found with id: "+id);
		}
		repo.deleteById(id);
	}
	public List<Patient> findByStaffId(Long staffId){
		return repo.findByStaffId(staffId);
	}
	
	public List<Patient> findByStatus(String status){
		return repo.findByStatus(status);
	}
	
	public List<Patient> findByName(String namePart){
		return repo.findByNameContainingIgnoreCase(namePart);
	}
	
	//patch
	public Patient partialUpdate(Long id,Patient patch) {
		return update(id ,patch);
	}
}
 