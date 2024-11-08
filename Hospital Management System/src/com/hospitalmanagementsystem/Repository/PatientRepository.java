package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Service.UserFactory;

import java.io.*;
import java.util.*;

public class PatientRepository {
    private final String filePath = "patients.csv"; // Path to the Patient CSV file
    private final Map<String, Patient> patients = new HashMap<>();

    public PatientRepository() {
        loadPatientsFromFile();
    }

    // Load patients from the CSV file
    private void loadPatientsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV is comma-separated
                if (data.length >= 5) { // Adjust for the fields in Patient CSV
                    String id = data[0];
                    String name = data[1];
                    String password = data[2];
                    String role = data[3];
                    String bloodType = data[4];

                    // Create patient using UserFactory
                    Patient patient = (Patient) UserFactory.createUser(id, name, password, role, bloodType);
                    if (patient != null) {
                        patients.put(id, patient);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading patients from file: " + e.getMessage());
        }
    }

    // Save all patients to the CSV file
    public void savePatientsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Patient patient : patients.values()) {
                bw.write(patient.getId() + "," + patient.getName() + "," + patient.getPassword() + "," + patient.getRole() + "," + patient.getBloodType());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients to file: " + e.getMessage());
        }
    }

    // Add a new patient and save to file
    public void addPatient(Patient patient) {
        if (patient != null && !patients.containsKey(patient.getId())) {
            patients.put(patient.getId(), patient);
            savePatientsToFile();
        } else {
            System.out.println("Patient already exists or invalid entry.");
        }
    }

    // Find a patient by ID
    public Optional<Patient> findPatientById(String id) {
        return Optional.ofNullable(patients.get(id));
    }

    // Update an existing patient
    public void updatePatient(Patient patient) {
        if (patient != null && patients.containsKey(patient.getId())) {
            patients.put(patient.getId(), patient);
            savePatientsToFile();
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Delete a patient
    public void deletePatient(String id) {
        if (patients.containsKey(id)) {
            patients.remove(id);
            savePatientsToFile();
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // List all patients (optional)
    public Map<String, Patient> getAllPatients() {
        return patients;
    }
}

