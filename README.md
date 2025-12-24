[POST]Create Bed - http://localhost:8090/api/beds 

{
  "ward": "ICU",
  "bedNumber": "IB-02",
  "roomNumber": "R-202",
  "isAvailable": true
}
[GET]Get Beds - http://localhost:8090/api/beds
[GET]Get Available beds - http://localhost:8090/api/beds/available

[POST] Create Staff - http://localhost:8090/api/staff
{
  "staffCode": "D101",
  "name": "Dr. Arjun Rao",
  "role": "Doctor",
  "department": "Cardiology",
  "phone": "9000000001",
  "isActive": true,
  "shift": "Morning"
}
{
  "staffCode": "N201",
  "name": "Nurse Anjali",
  "role": "Nurse",
  "department": "Cardiology",
  "phone": "9000000011",
  "isActive": true,
  "shift": "Morning"
}

[GET] Get Staff - http://localhost:8090/api/staff
[GET] http://localhost:8090/api/staff
[GET] http://localhost:8090/api/staff/department/cardiology
[GET] http://localhost:8090/api/staff/role/nurse
[GET] http://localhost:8090/api/staff/search?q=Dr. Rahul

[PUT]Update Staff - http://localhost:8090/api/staff/6
{
  "name": "Kavya",
  "role": "Nurse",
  "department": "Pediatrics",
  "phone": "9000000013",
  "isActive": true,
  "shift": "Night"
}

[POST] Create equipment - http://localhost:8090/api/equipment-tracking
{
  "equipCode": "ECG101",
  "equipName": "ECG Machine",
  "status": "Available",
  "assignedAt": null,
  "releasedAt": null
}
[PUT] update equipment - http://localhost:8090/api/equipment-tracking/3
{
  "status": "AVAILABLE",
  "assignedAt": "2025-12-24T10:30:00",
  "releasedAt": "2025-12-24T14:45:00"
}

[POST] Create Patient - http://localhost:8090/api/patients
{
  "name": "Ravi Kumar",
  "dob": "1990-05-12",
  "gender": "Male",
  "contact": "9876543210",
  "status": "ADMITTED",
  "admissionDate": "2025-12-24T09:30:00",
  "medicalHisSummary": "High fever"
}
[PUT] assign staff - http://localhost:8090/api/patients/1/assign-staff/2
						       /1/remove-staff/2

[GET] get by staff - http://localhost:8090/api/patients/staff/1

[PUT] discharge patient - http://localhost:8090/api/patients/2/discharge
{
  "status": "DISCHARGED",
  "dischargeDate": "2025-12-25T14:00:00"
}


Allocate bed
[GET]Check available - http://localhost:8090/api/beds/available

[POST] Create bedAllocation - http://localhost:8090/api/bed-alloc
{
  "patientId": 2,
  "bedId": 1,
  "allocationStart": "2025-12-24T10:00:00",
  "reason": "General admission",
  "allocationEnd": null
}

