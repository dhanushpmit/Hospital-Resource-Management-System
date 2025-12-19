package com.example.HospitalResource.exception;

public class BedAllocationNotFoundException extends RuntimeException {
	public BedAllocationNotFoundException(String message) {
		super(message);
	}

}
