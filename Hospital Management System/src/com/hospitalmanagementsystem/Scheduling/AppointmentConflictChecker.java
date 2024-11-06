package com.hospitalmanagementsystem.Scheduling;

import java.util.List;
import com.hospitalmanagementsystem.Model.Appointment;

public class AppointmentConflictChecker {

    // Check for conflicting appointments
    public boolean checkForConflicts(Appointment newAppointment, List<Appointment> existingAppointments) {
        for (Appointment appointment : existingAppointments) {
            if (appointment.getDate().equals(newAppointment.getDate()) &&
                    appointment.getDoctor().equals(newAppointment.getDoctor())) {
                return true; // Conflict found
            }
        }
        return false; // No conflicts
    }

    // Resolve appointment conflicts
    public void resolveConflict(Appointment conflictingAppointment, Appointment newAppointment) {
        System.out.println("Conflict detected between " +
                conflictingAppointment.getAppointmentID() +
                " and " + newAppointment.getAppointmentID());
        System.out.println("Please reschedule one of the appointments.");
    }
}
