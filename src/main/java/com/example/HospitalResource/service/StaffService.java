package com.example.HospitalResource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HospitalResource.entity.Staff;
import com.example.HospitalResource.repository.StaffRepository;

@Service
public class StaffService {
	private final StaffRepository repo;
	
	public StaffService(StaffRepository repo) {
		this.repo=repo;
	}
	
	public List<Staff> findAll(){
		return repo.findAll();
	}
	
	public Optional<Staff> findById(Long id){
		return repo.findById(id);
	}
	
	public Staff create(Staff staff) {
		return repo.save(staff);
	}
	
	public Staff update(Long id,Staff incoming) {
		return repo.findById(id)
				.map(existing->{
					if(incoming.getStaffCode()!=null) existing.setStaffCode(incoming.getStaffCode());
					if(incoming.getName()!=null) existing.setName(incoming.getName());
					if(incoming.getRole()!=null) existing.setName(incoming.getName());
					if(incoming.getDepartment()!=null) existing.setName(incoming.getDepartment());
					if(incoming.getPhone()!=null) existing.setPhone(incoming.getPhone());
					if(incoming.getIsActive()!=null) existing.setIsActive(incoming.getIsActive());
					if(incoming.getShift()!=null) existing.setShift(incoming.getShift());
					
					return repo.save(existing);
				}).orElseThrow(()->
				new RuntimeException("Staff not found with id: "+id));
	}
	
	public void deleteById(Long id) {
		if(!repo.existsById(id)) {
			throw new RuntimeException("Staff not found with id: "+id);
		}
		repo.deleteById(id);
	}
	
	public List<Staff> findByDepartment(String department){
		return repo.findByDepartment(department);
	}
	
	public List<Staff> findByRole(String role){
		return repo.findByRole(role);
	}
	
	public List<Staff> findByActive(Boolean isActive){
		return repo.findByIsActive(isActive);
	}
	
	public List<Staff> findByShift(String shift){
		return repo.findByShift(shift);
	}
	
	public List<Staff> searchByName(String namePart){
		return repo.findByNameContainingIgnoreCase(namePart);
	}
	
	public Staff partialUpdate(Long id,Staff patch) {
		return update(id,patch);
	}
}