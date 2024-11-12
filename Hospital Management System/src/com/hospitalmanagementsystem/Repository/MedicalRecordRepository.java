package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MedicalRecordRepository {
    private final Map<String, MedicalRecord> medicalRecords = new HashMap<>();

    // Add a new medical record
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord != null && !medicalRecords.containsKey(medicalRecord.getPatientID())) {
            medicalRecords.put(medicalRecord.getPatientID(), medicalRecord);
            System.out.println("Medical record added for patient ID: " + medicalRecord.getPatientID());
        } else {
            System.out.println("Medical record already exists or invalid entry.");
        }
    }

    // Find a medical record by patient ID
    public Optional<MedicalRecord> findMedicalRecordById(String patientID) {
        return Optional.ofNullable(medicalRecords.get(patientID));
    }

    // Update an existing medical record
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord != null && medicalRecords.containsKey(medicalRecord.getPatientID())) {
            medicalRecords.put(medicalRecord.getPatientID(), medicalRecord);
            System.out.println("Medical record updated for patient ID: " + medicalRecord.getPatientID());
        } else {
            System.out.println("Medical record not found.");
        }
    }

    // Delete a medical record by patient ID
    public void deleteMedicalRecord(String patientID) {
        if (medicalRecords.containsKey(patientID)) {
            medicalRecords.remove(patientID);
            System.out.println("Medical record deleted for patient ID: " + patientID);
        } else {
            System.out.println("Medical record not found.");
        }
    }

    // List all medical records (optional)
    public Map<String, MedicalRecord> getAllMedicalRecords() {
        return new HashMap<>(medicalRecords);
    }
}
