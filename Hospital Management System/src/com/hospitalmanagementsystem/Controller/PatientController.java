package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PatientController {
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<User> loginPatient(Scanner scanner, List<Patient> patients) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String patientPassword = scanner.nextLine();

        for (Patient patient : patients) {
            if (patient.getId().equals(patientId) && patient.getPassword().equals(patientPassword)) {
                return Optional.of(patient); // Return the patient if login is successful
            }
        }
        return Optional.empty(); // Return empty if login failed
    }


    public void viewMedicalRecord() {
        System.out.println(medicalRecord.getRecordDetails()); // Access via getter method
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void updatePersonalInformation(Scanner scanner) {
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();
        medicalRecord.setContactInfo(newEmail, newPhone);
        System.out.println("Personal information updated.");
    }

    public void viewAvailableAppointments(List<Doctor> doctors) {
        System.out.println("Available appointment slots:");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor " + doctor.getName() + " availability: " + doctor.getAvailableSlots());
        }
    }

    public void scheduleAppointment(Scanner scanner, List<Doctor> doctors) {
        System.out.println("Select Doctor by ID:");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.name + ", ID: " + doctor.id);
        }
        String doctorId = scanner.nextLine();
        Doctor selectedDoctor = null;

        for (Doctor doctor : doctors) {
            if (doctor.id.equals(doctorId)) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor != null) {
            System.out.print("Enter preferred date and time: ");
            String date = scanner.nextLine();
            Appointment appointment = new Appointment("A" + (appointments.size() + 1), selectedDoctor, this, date);
            appointments.add(appointment);
            selectedDoctor.addAppointment(appointment);
            System.out.println("Appointment scheduled: " + appointment);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void rescheduleAppointment(Scanner scanner) {
        System.out.print("Enter the appointment ID to reschedule: ");
        String appointmentID = scanner.nextLine();
        Appointment appointmentToReschedule = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                appointmentToReschedule = appointment;
                break;
            }
        }

        if (appointmentToReschedule != null) {
            System.out.print("Enter new date and time: ");
            String newDate = scanner.nextLine();
            Appointment newAppointment = new Appointment(appointmentToReschedule.getAppointmentID(), appointmentToReschedule.getDoctor(), this, newDate);
            appointments.remove(appointmentToReschedule);
            appointments.add(newAppointment);
            appointmentToReschedule.getDoctor().updateAppointment(appointmentToReschedule, newAppointment);
            System.out.println("Appointment rescheduled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void cancelAppointment(Scanner scanner) {
        System.out.print("Enter the appointment ID to cancel: ");
        String appointmentID = scanner.nextLine();
        Appointment appointmentToCancel = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                appointmentToCancel = appointment;
                break;
            }
        }

        if (appointmentToCancel != null) {
            appointments.remove(appointmentToCancel);
            appointmentToCancel.getDoctor().cancelAppointment(appointmentToCancel);
            System.out.println("Appointment canceled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void viewScheduledAppointments() {
        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void viewPastAppointmentOutcomes() {
        System.out.println("Past Appointment Outcomes:");
        for (Appointment appointment : appointments) {
            if (appointment.isCompleted()) {
                System.out.println(appointment.getOutcomeRecord().viewOutcome());
            }
        }
    }

    public void viewBillingDetails() {
        billing.viewBill(); // Display billing details
    }

}

