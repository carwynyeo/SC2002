package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.MedicalRecord.Prescription;
import com.hospitalmanagementsystem.Scheduling.AppointmentOutcomeRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private static List<Appointment> allAppointments = new ArrayList<>();

    private final String appointmentID;
    private LocalDateTime appointmentTime;
    private final Doctor doctor;
    private final Patient patient;
    private final String date;
    private String status;
    private boolean isCompleted;
    private Prescription prescription;
    private final AppointmentOutcomeRecord outcomeRecord;

    public Appointment(String appointmentID, Doctor doctor, Patient patient, String date, LocalDateTime appointmentTime) {
        this.appointmentID = appointmentID;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.status = "Pending";
        this.isCompleted = false;
        this.outcomeRecord = new AppointmentOutcomeRecord(appointmentID, doctor.getId(), patient.getId(), date);
        this.prescription = new Prescription();
        allAppointments.add(this);
    }

    public static List<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public AppointmentOutcomeRecord getOutcomeRecord() {
        return outcomeRecord;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Doctor: " + doctor.getName() + ", Patient: " + patient.getName() +
                ", Date: " + date + ", Status: " + status + ", Completed: " + isCompleted;
    }

}
