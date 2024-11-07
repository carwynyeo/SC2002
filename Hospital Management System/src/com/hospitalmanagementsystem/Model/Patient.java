package com.hospitalmanagementsystem.Model;


import com.hospitalmanagementsystem.Billing.Billing;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Billing.Payment;


import java.util.ArrayList;
import java.util.List;

public class Patient extends User{
    private List<Appointment> appointments;
    private MedicalRecord medicalRecord;
    private Billing billing;
    private String doctorId;

    //remember to link doctorId, such that when a patient is appended to a doctor, the doctorId field is filled
    public Patient(String id, String name, String password, MedicalRecord medicalRecord) {
        super(id, name, password, "Patient");
        this.appointments = new ArrayList<>(); // Initialize the list
        this.medicalRecord = medicalRecord; // Initialize the medical record
        this.billing = new Billing();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public List<Appointment> getAppointments() { return appointments; }

    // Getter and setter for doctorId
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    // Add an appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}