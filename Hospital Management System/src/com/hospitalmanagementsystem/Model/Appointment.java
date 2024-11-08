package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.MedicalRecord.Prescription;
import com.hospitalmanagementsystem.Scheduling.AppointmentOutcomeRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private static final List<Appointment> allAppointments = new ArrayList<>();

    private final String appointmentID;
    private LocalDateTime appointmentTime;
    private final Doctor doctor;
    private final Patient patient;
    private String date;
    private String status;
    private boolean isCompleted;
    private Prescription prescription;
    private AppointmentOutcomeRecord outcomeRecord;

    // appointment statuses
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_CONFIRMED = "Confirmed";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_CANCELED = "Canceled";

    public Appointment(String appointmentID, Doctor doctor, Patient patient, String date, LocalDateTime appointmentTime,
            String medicationName) {
        this.appointmentID = appointmentID;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.status = "Pending";
        this.isCompleted = false;
        this.outcomeRecord = new AppointmentOutcomeRecord(appointmentID, doctor.getId(), patient.getId(), date,
                "Initial consultation notes"); // Pass initial consultation notes as needed
        this.prescription = new Prescription(medicationName); // Update to use new constructor
        allAppointments.add(this);
    }

    public static List<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getStatus() {
        return status; // Return current status
    }

    public void setStatus(String status) {
        this.status = status; // Set status using string literals
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public AppointmentOutcomeRecord getOutcomeRecord(String consultationNotes) {
        // Create a new outcome record if it doesn't exist
        if (outcomeRecord == null) {
            outcomeRecord = new AppointmentOutcomeRecord(appointmentID, doctor.getId(), patient.getId(), date,
                    consultationNotes);
        } else {
            // Update the existing outcome record's consultation notes
            outcomeRecord.setConsultationNotes(consultationNotes);
        }
        return outcomeRecord;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription; // Set the prescription for this appointment
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // New method for rescheduling the appointment
    public void reschedule(LocalDateTime newAppointmentTime, String newDate) {
        this.appointmentTime = newAppointmentTime; // Update appointment time
        this.date = newDate; // Update date
        this.status = STATUS_PENDING; // Reset status to pending upon rescheduling
        System.out.println("Appointment " + appointmentID + " rescheduled to " + newDate + " at " + newAppointmentTime);
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Doctor: " + doctor.getName() + ", Patient: " + patient.getName()
                +
                ", Date: " + date + ", Status: " + status + ", Completed: " + isCompleted;
    }
}