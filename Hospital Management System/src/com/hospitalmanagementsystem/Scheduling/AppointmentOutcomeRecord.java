package com.hospitalmanagementsystem.Scheduling;


public class AppointmentOutcomeRecord {
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private String date;
    private String servicesProvided;
    private String prescriptions;

    public AppointmentOutcomeRecord(String appointmentId, String doctorId, String patientId, String date) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.servicesProvided = "";
        this.prescriptions = "";
    }

    public void recordService(String service) {
        servicesProvided += service + "; ";
        System.out.println("Service recorded: " + service);
    }

    public void recordPrescription(String prescription) {
        prescriptions += prescription + "; ";
        System.out.println("Prescription recorded: " + prescription);
    }

    public void viewOutcome() {
        System.out.println("Appointment Outcome for " + appointmentId + ":");
        System.out.println("Services Provided: " + servicesProvided);
        System.out.println("Prescriptions: " + prescriptions);
    }
}

