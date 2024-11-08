package com.hospitalmanagementsystem.Scheduling;

public class AppointmentOutcomeRecord {
    private String appointmentId;
    private String doctorId;
    private final String patientId;
    private String date;
    private String servicesProvided;
    private String prescriptions;
    private String consultationNotes;
    private String status;

    public AppointmentOutcomeRecord(String appointmentId, String doctorId, String patientId, String date,
            String consultationNotes) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.servicesProvided = "";
        this.prescriptions = "";
        this.consultationNotes = consultationNotes;
        this.status = "Pending"; // default status
    }

    public void recordService(String service) {
        servicesProvided += service + "; ";
        System.out.println("Service recorded: " + service);
    }

    public void recordPrescription(String prescription) {
        prescriptions += prescription + "; ";
        System.out.println("Prescription recorded: " + prescription);
    }

    // threw doctorId, patientId, serviceProvided and status in to check if
    // attributes are properly saved. (can remove)
    public void viewOutcome() {
        System.out.println("Appointment Outcome for " + appointmentId + ":");
        System.out.println("Doctor ID: " + doctorId); // can remove
        System.out.println("Patient ID: " + patientId); // can remove
        System.out.println("Date: " + date); // Display appointment date
        System.out.println("Services Provided: " + servicesProvided); // can remove
        System.out.println("Prescriptions: " + prescriptions);
        System.out.println("Consultation Notes: " + consultationNotes);
        System.out.println("Status: " + status); // Display the status
    }

    public void setStatus(String status) {
        this.status = status; // Method to set the outcome status
    }

    public String getStatus() {
        return status; // Method to get the current outcome status
    }

    // New method to set consultation notes
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes; // Update consultation notes
    }

    public String getConsultationNotes() {
        return consultationNotes; // Method to get the current consultation notes
    }
}
