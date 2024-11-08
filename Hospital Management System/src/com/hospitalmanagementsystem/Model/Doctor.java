package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.MedicalRecord.PrescriptionManager;
import com.hospitalmanagementsystem.Scheduling.Schedule;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Staff {
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

    // Getters for fields
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public PrescriptionManager getPrescriptionManager() {
        return prescriptionManager;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Logger getLogger() {
        return logger;
    }

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

    public void recordOutcome(Appointment appointment, String outcome, String consultationNotes) {
        appointment.getOutcomeRecord().recordService(outcome);
        appointment.getOutcomeRecord().setConsultationNotes(consultationNotes); // Set the consultation notes
        logger.logInfo("Recorded outcome for appointment " + appointment.getAppointmentID() + " with notes: "
                + consultationNotes);
    }

    // Method to update an existing appointment
    public void updateAppointment(Appointment oldAppointment, Appointment newAppointment) {
        // Remove the old appointment from the list
        removeAppointment(oldAppointment);

        // Add the new appointment to the list
        addAppointment(newAppointment);

        // Log the update action
        logger.logInfo("Updated appointment from " + oldAppointment + " to " + newAppointment);
    }
}
