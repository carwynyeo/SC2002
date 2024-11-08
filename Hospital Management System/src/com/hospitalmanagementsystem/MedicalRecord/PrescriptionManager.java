package com.hospitalmanagementsystem.MedicalRecord;

import com.hospitalmanagementsystem.Model.Appointment;

public class PrescriptionManager {

    // Method to create a prescription for a given appointment
    public void createPrescription(Appointment appointment, String medication) {
        if (appointment == null || medication == null || medication.isEmpty()) {
            System.out.println("Invalid appointment or medication name.");
            return; // Exit the method if invalid
        }

        Prescription prescription = new Prescription(medication);
        appointment.setPrescription(prescription); // Set the prescription in the appointment
        System.out.println("Prescription created for " + appointment.getPatient().getName());
    }

    // Method to view the status of a prescription for a given appointment
    public void viewPrescriptionStatus(Appointment appointment) {
        if (appointment == null || appointment.getPrescription() == null) {
            System.out.println("No prescription found for this appointment.");
            return; // Exit if there's no prescription
        }

        System.out.println("Prescription Status: " + appointment.getPrescription().getStatus());
    }

    // Method to update the status of a prescription for a given appointment
    public void updatePrescription(Appointment appointment, String newStatus) {
        if (appointment == null || appointment.getPrescription() == null) {
            System.out.println("No prescription found for this appointment.");
            return; // Exit if there's no prescription
        }

        appointment.getPrescription().setStatus(newStatus);
        System.out.println("Prescription status updated to " + newStatus);
    }
}
