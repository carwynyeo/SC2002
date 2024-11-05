package com.hospitalmanagementsystem.MedicalRecord;

import com.hospitalmanagementsystem.Model.Appointment;

public class PrescriptionManager {

    public void createPrescription(Appointment appointment, String medication) {
        Prescription prescription = new Prescription(medication);
        appointment.getPrescription().setMedicationName(medication);
        System.out.println("Prescription created for " + appointment.getPatient().getName());
    }

    public void viewPrescriptionStatus(Appointment appointment) {
        System.out.println("Prescription Status: " + appointment.getPrescription().getStatus());
    }

    public void updatePrescription(Appointment appointment, String newStatus) {
        appointment.getPrescription().setStatus(newStatus);
        System.out.println("Prescription status updated to " + newStatus);
    }
}

