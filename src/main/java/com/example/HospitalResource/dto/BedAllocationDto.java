package com.example.HospitalResource.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BedAllocationDto {

    private Long patientId;          
    private Long bedId;
    private LocalDateTime allocationStart;
    private String reason;
}
