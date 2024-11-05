package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.MedicalRecord.PrescriptionManager;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{
    private List<Appointment> appointments = new ArrayList<>();
    private List<Appointment> availableSlots = new ArrayList<>();
    private PrescriptionManager prescriptionManager;
    private Schedule schedule;
    private Logger logger;

    public Doctor(String id, String name, String password) {
        super(id, name, password, "Doctor");
        this.appointments = new ArrayList<>();
        this.prescriptionManager = new PrescriptionManager();
        this.schedule = new Schedule(id);
        this.logger = new Logger();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public List<Appointment> getAppointments() { return appointments; }

    // Methods for managing appointments
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}

