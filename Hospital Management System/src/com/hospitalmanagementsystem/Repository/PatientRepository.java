package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    private final List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        return patients;
    }
}

