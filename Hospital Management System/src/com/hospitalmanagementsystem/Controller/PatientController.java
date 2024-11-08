package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.DoctorRepository;
import com.hospitalmanagementsystem.Repository.PatientRepository;
import com.hospitalmanagementsystem.Service.ChangePasswordService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PatientController extends UserController {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    public PatientController(ChangePasswordService changePasswordService, PatientRepository patientRepository) {
        super(changePasswordService);
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Optional<User> loginPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String patientPassword = scanner.nextLine();

        Patient patient = patientRepository.findPatientById(patientId);
        if (patient != null && patient.getPassword().equals(patientPassword)) {
            return Optional.of(patient);
        }
        return Optional.empty();
    }

    public void viewMedicalRecord(Patient patient) {
        System.out.println(patient.getMedicalRecord().getRecordDetails());
    }

    public void updatePersonalInformation(Patient patient,Scanner scanner) {
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();
        patient.getMedicalRecord().setContactInfo(newEmail, newPhone);
        System.out.println("Personal information updated.");
    }

    //Fix function
    public void viewAvailableAppointments(Patient patient,Scanner scanner) {
        List<Doctor> doctors = doctorRepository.getAllDoctors();
        System.out.println("Available appointment slots:");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor " + doctor.getName() + " availability: " + doctor.getAvailableSlots());
        }
    }

    public void scheduleAppointment(Patient patient,Scanner scanner) {
        List<Doctor> doctors = doctorRepository.getAllDoctors(); // Fetch the list of doctors
        System.out.println("Select Doctor by ID:");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.getName() + ", ID: " + doctor.getId());
        }
        String doctorId = scanner.nextLine();
        Doctor selectedDoctor = null;

        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(doctorId)) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor != null) {
            System.out.print("Enter preferred date and time: ");
            String date = scanner.nextLine();
            Appointment appointment = new Appointment("A" + (patient.getAppointments().size() + 1), selectedDoctor, patient, date);
            patient.addAppointment(appointment);
            selectedDoctor.addAppointment(appointment);
            System.out.println("Appointment scheduled: " + appointment);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void rescheduleAppointment( Patient patient,Scanner scanner) {
        System.out.print("Enter the appointment ID to reschedule: ");
        String appointmentID = scanner.nextLine();
        Appointment appointmentToReschedule = null;
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                appointmentToReschedule = appointment;
                break;
            }
        }

        if (appointmentToReschedule != null) {
            System.out.print("Enter new date and time: ");
            String newDate = scanner.nextLine();
            Appointment newAppointment = new Appointment(appointmentToReschedule.getAppointmentID(), appointmentToReschedule.getDoctor(), patient, newDate);
            patient.getAppointments().remove(appointmentToReschedule);
            patient.getAppointments().add(newAppointment);
            appointmentToReschedule.getDoctor().updateAppointment(appointmentToReschedule, newAppointment);
            System.out.println("Appointment rescheduled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void cancelAppointment( Patient patient,Scanner scanner) {
        System.out.print("Enter the appointment ID to cancel: ");
        String appointmentID = scanner.nextLine();
        Appointment appointmentToCancel = null;
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                appointmentToCancel = appointment;
                break;
            }
        }

        if (appointmentToCancel != null) {
            patient.getAppointments().remove(appointmentToCancel);
            appointmentToCancel.getDoctor().cancelAppointment(appointmentToCancel);
            System.out.println("Appointment canceled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void viewScheduledAppointments(Patient patient) {
        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : patient.getAppointments()) {
            System.out.println(appointment);
        }
    }

    public void viewPastAppointmentOutcomes(Patient patient) {
        System.out.println("Past Appointment Outcomes:");
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.isCompleted()) {
                System.out.println(appointment.getOutcomeRecord().viewOutcome());
            }
        }
    }

    public void viewBillingDetails(Patient patient) {
        System.out.println(patient.getBillings());; // Display billing details
    }

    @Override
    public void createUserAccount(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        String defaultPassword = "password";
        // Create new patient if ID is unique
        Patient newPatient = new Patient(id, name, defaultPassword, new MedicalRecord(id, name, "O+"));
        patientRepository.addPatient(newPatient); // Add to repository
        System.out.println("Patient account created successfully!");
    }
}

