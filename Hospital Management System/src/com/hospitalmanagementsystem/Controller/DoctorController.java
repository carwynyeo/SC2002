package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.DoctorRepository;
import com.hospitalmanagementsystem.Scheduling.AppointmentConflictChecker;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;
import com.hospitalmanagementsystem.Utility.Logger;
import com.hospitalmanagementsystem.Scheduling.Schedule;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DoctorController {
    private DoctorRepository doctorRepository;
    private final AppointmentConflictChecker conflictChecker = new AppointmentConflictChecker();
    private final Logger logger = new Logger();

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Optional<User> loginDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String doctorPassword = scanner.nextLine();

        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor != null && doctor.getPassword().equals(doctorPassword)) {
            return Optional.of(doctor);
        }
        return Optional.empty();
    }


    public void viewPatientMedicalRecord(List<Patient> patients, Scanner scanner) {
        System.out.print("Enter Patient ID to view medical records: ");
        String patientId = scanner.nextLine();

        // Find the patient by their ID
        Patient selectedPatient = null;
        for (Patient patient : patients) {
            if (patient.id.equals(patientId)) {
                selectedPatient = patient;
                break;
            }
        }

        // Display the patient's medical record if found
        if (selectedPatient != null) {
            System.out.println("Medical Record for " + selectedPatient.name + ":");
            System.out.println(selectedPatient.getMedicalRecord().getRecordDetails());
        } else {
            System.out.println("Patient not found.");
        }
        System.out.println("Medical Record for " + patient.getName() + ": " + patient.getMedicalRecord().getRecordDetails());
        logger.logInfo("Doctor viewed medical record for patient: " + patient.getName());
    }

    public void updatePatientMedicalRecord(List<Patient> patients, Scanner scanner) {
        System.out.print("Enter Patient ID to update medical records: ");
        String patientId = scanner.nextLine();

        Patient selectedPatient = null;
        for (Patient patient : patients) {
            if (patient.id.equals(patientId)) {
                selectedPatient = patient;
                break;
            }
        }

        if (selectedPatient != null) {
            System.out.print("Enter new diagnosis: ");
            String diagnosis = scanner.nextLine();
            System.out.print("Enter new treatment: ");
            String treatment = scanner.nextLine();

            selectedPatient.getMedicalRecord().addDiagnosis(diagnosis);
            selectedPatient.getMedicalRecord().addTreatment(treatment);
            System.out.println("Patient medical record updated.");
            logger.logInfo("Updated medical record for patient: " + patient.getName());
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void setAvailability(Doctor doctor, TimeSlot slot) {
        doctor.addAvailableSlot(slot);
        logger.logInfo("Doctor " + doctor.getName() + " set availability for " + slot.getDate());
    }


    public void manageAppointmentRequest(Doctor doctor, Appointment appointment, boolean accept) {
        if (accept) {
            doctor.addAppointment(appointment);
            appointment.setStatus("Confirmed");
            logger.logInfo("Doctor " + doctor.getName() + " accepted appointment for patient: " + appointment.getPatient().getName());
        } else {
            appointment.setStatus("Declined");
            logger.logInfo("Doctor " + doctor.getName() + " declined appointment for patient: " + appointment.getPatient().getName());
        }
    }

    public void recordAppointmentOutcome(Doctor doctor, Appointment appointment, String outcome) {
        doctor.recordOutcome(appointment, outcome);
        appointment.setCompleted(true);
        logger.logInfo("Recorded outcome for appointment: " + appointment.getAppointmentID());
    }

    public void viewPersonalSchedule(Doctor doctor) {
        System.out.println("Personal Schedule for " + name + ":");
        if (doctor.getAppointments().isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            doctor.getAppointments().forEach(System.out::println);
        }
    }

    public void viewUpcomingAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
    
    public List<Appointment> getAvailableSlots() {
        return availableSlots;
    }

    public void createAppointment(String doctorId, Appointment appointment) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor != null) {
            if (!conflictChecker.checkForConflicts(appointment, Appointment.getAllAppointments())) {
                doctor.getSchedule().addAvailableSlot(new TimeSlot(
                        appointment.getAppointmentID(),
                        appointment.getDate(),
                        appointment.getAppointmentTime().toLocalTime().toString(),
                        appointment.getAppointmentTime().plusHours(1).toLocalTime().toString()
                ));
                System.out.println("Appointment scheduled: " + appointment);
            } else {
                System.out.println("Appointment conflict detected.");
            }
        }
    }
    public void updateAppointment(Appointment oldAppointment, Appointment newAppointment) {
        appointments.remove(oldAppointment);
        appointments.add(newAppointment);
    }

    public void cancelAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.getAllDoctors();
    }
}
