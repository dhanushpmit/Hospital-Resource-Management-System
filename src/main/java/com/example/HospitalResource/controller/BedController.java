package com.example.HospitalResource.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HospitalResource.entity.Bed;
import com.example.HospitalResource.repository.BedRepository;

@RestController
@RequestMapping("api/beds")
public class BedController {
	private final BedRepository bedRepository;
	
	public BedController(BedRepository bedRepository) {
		this.bedRepository=bedRepository;
	}

	@PostMapping
    public Bed create(@RequestBody Bed bed) {
        bed.setIsAvailable(true);
        return bedRepository.save(bed);
    }
	
	@GetMapping
	public List<Bed> findAllBed(){
		return bedRepository.findAll();
	}

    @GetMapping("/available")
    public List<Bed> availableBeds() {
        return bedRepository.findByIsAvailable(true);
    }
    
   
}
