package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.Billing.Billing;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Billing.Payment;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private String gender;
    private String dob; // Date of Birth
    private String name;
    private List<Appointment> appointments;
    private List<MedicalRecord> pastMedicalRecords;
    private MedicalRecord medicalRecord;
    private List<Billing> billings;
    private String doctorId;

    // Constructor
    public Patient(String id, String name, String password, MedicalRecord medicalRecord) {
        super(id, name, password, "Patient");
        this.appointments = new ArrayList<>(); // Initialize the list
        this.pastMedicalRecords = new ArrayList<>(); // Initialize the past medical records
        this.billings = new ArrayList<>(); // Initialize the billing list
        this.medicalRecord = medicalRecord;
    }

    // Getters and setters
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }

    public List<MedicalRecord> getPastMedicalRecords() { return pastMedicalRecords; }
    public void setPastMedicalRecords(List<MedicalRecord> pastMedicalRecords) { this.pastMedicalRecords = pastMedicalRecords; }

    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public void setMedicalRecord(MedicalRecord medicalRecord) { this.medicalRecord = medicalRecord; }

    public List<Billing> getBillings() { return billings; }
    public void setBillings(List<Billing> billings) { this.billings = billings; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    // Method to add an appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
