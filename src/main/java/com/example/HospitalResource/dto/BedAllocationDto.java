package com.example.HospitalResource.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BedAllocationDto {

    private Long patientId;          
    private String ward;
    private String bedNumber;
    private String roomNumber;
    private Boolean isOccupied;
    private LocalDateTime allocationStart;
    private LocalDateTime allocationEnd;
}
