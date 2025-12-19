package com.example.HospitalResource.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EquipmentTrackingDto {

    private String equipCode;
    private String equipName;
    private String status;
    private LocalDateTime assignedAt;
    private LocalDateTime releasedAt;
    private Long patientId;  
}
