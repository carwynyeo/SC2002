package com.hospitalmanagementsystem.Scheduling;



//Need to make this able to show "completed", "pending", "confirmed".
public class AppointmentOutcomeRecord {
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private String date;
    private String servicesProvided;
    private String prescriptions;
    private String consultationNotes;

    public AppointmentOutcomeRecord(String appointmentId, String doctorId, String patientId, String date, String consultationNotes) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.servicesProvided = ""; //Type of Service provided (e.g. consultation, X-Ray, blood Test, etc)
        this.prescriptions = "";//Any prescribed medications: medication name, status (default is pending)
        this.consultationNotes = consultationNotes;
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

