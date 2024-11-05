package com.hospitalmanagementsystem.MedicalRecord;

public class Prescription {
    private String medicationName;
    private String status;

    public Prescription() {
        this.medicationName = "N/A";
        this.status = "Pending";
    }

    public Prescription(String medicationName) {
        this.medicationName = medicationName;
        this.status = "Pending"; // default status
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Prescription[Medication: " + medicationName + ", Status: " + status + "]";
    }
}
