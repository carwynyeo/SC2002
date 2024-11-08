package com.hospitalmanagementsystem.MedicalRecord;

public class Prescription {
    private final String medicationName; // final to prevent changes
    private String status;

    // added medicationName arg to constructor
    public Prescription(String medicationName) {
        this.medicationName = medicationName;
        this.status = "Pending"; // default status
    }

    // Getter for medication name
    public String getMedicationName() {
        return medicationName;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Prescription [Medication: %s, Status: %s]", medicationName, status);
    }
}
