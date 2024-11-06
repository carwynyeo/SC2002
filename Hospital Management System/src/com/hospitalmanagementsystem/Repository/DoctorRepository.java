package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Doctor findDoctorById(String id) {
        return doctors.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public Appointment findAppointmentById(String appointmentId) {
        for (Doctor doctor : doctors) {
            for (Appointment appointment : doctor.getAppointments()) {
                if (appointment.getAppointmentID().equals(appointmentId)) {
                    return appointment;
                }
            }
        }
        return null;

        public List<Doctor> getAllDoctors() {
            return doctors;
        }
    }
}
