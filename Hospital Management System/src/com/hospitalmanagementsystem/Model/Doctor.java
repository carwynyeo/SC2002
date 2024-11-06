package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.MedicalRecord.PrescriptionManager;
import com.hospitalmanagementsystem.Scheduling.Schedule;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{
    private final List<Appointment> appointments = new ArrayList<>();
    private final List<TimeSlot> availableSlots = new ArrayList<>();
    private final PrescriptionManager prescriptionManager;
    private final Schedule schedule;
    private final Logger logger;

    public Doctor(String id, String name, String password) {
        super(id, name, password, "Doctor");
        this.prescriptionManager = new PrescriptionManager();
        this.schedule = new Schedule(id);
        this.logger = new Logger();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    // Getters and setters
    public List<Appointment> getAppointments() { return appointments; }
    public List<TimeSlot> getAvailableSlots() { return availableSlots; }
    public Schedule getSchedule() { return schedule; }

    // Appointment management methods
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addAvailableSlot(TimeSlot slot) {
        availableSlots.add(slot);
        schedule.addAvailableSlot(slot);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public void recordOutcome(Appointment appointment, String outcome) {
        appointment.getOutcomeRecord().recordService(outcome);
    }
}

