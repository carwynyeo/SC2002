package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();

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
    }
        public List<Doctor> getAllDoctors() {
            return doctors;
        }
    // Method to get available slots for a doctor
    public List<TimeSlot> getAvailableSlots(String doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        return doctor != null ? doctor.getAvailableSlots() : List.of(); // returns empty list if doctor not found
    }

    // Method to get appointments for a doctor
    public List<Appointment> getAppointments(String doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        return doctor != null ? doctor.getAppointments() : List.of(); // returns empty list if doctor not found
    }

    public List<Patient> getPatientsByDoctorId(String doctorId) {
        // Assuming patients have a reference to their assigned doctor
        List<Patient> doctorPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getDoctorId().equals(doctorId)) {
                doctorPatients.add(patient);
            }
        }
        return doctorPatients;
    }
    // Add a patient to the list
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Update a patient's information
    public void updatePatient(Patient patient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(patient.getId())) {
                patients.set(i, patient);
                return;
            }
        }
    }

    // Assign a patient to a doctor
    public void assignPatientToDoctor(String patientId, String doctorId) {
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                patient.setDoctorId(doctorId);  // Associate patient with the doctor
                return;
            }
        }
    }
}

