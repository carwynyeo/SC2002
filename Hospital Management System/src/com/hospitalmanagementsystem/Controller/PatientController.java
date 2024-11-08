package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.DoctorRepository;
import com.hospitalmanagementsystem.Repository.PatientRepository;
import com.hospitalmanagementsystem.Service.ChangePasswordService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PatientController extends UserController {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    public PatientController(ChangePasswordService changePasswordService, PatientRepository patientRepository,
            DoctorRepository doctorRepository) {
        super(changePasswordService);
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
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

    public void updatePersonalInformation(Scanner scanner, Patient patient) {
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();
        patient.getMedicalRecord().setContactInfo(newEmail, newPhone);
        System.out.println("Personal information updated.");
    }

    public void viewAvailableAppointments(List<Doctor> doctors) {
        System.out.println("Available appointment slots:");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor " + doctor.getName() + " availability: " + doctor.getAvailableSlots());
        }
    }

    public void scheduleAppointment(Scanner scanner, Patient patient) {
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
            System.out.print("Enter preferred date (yyyy-mm-dd): ");
            String date = scanner.nextLine();
            System.out.print("Enter preferred time (HH:mm): ");
            String time = scanner.nextLine();
            // Combine date and time to create LocalDateTime
            LocalDateTime appointmentTime = LocalDateTime.parse(date + "T" + time); // Ensure this format is valid
            Appointment appointment = new Appointment("A" + (patient.getAppointments().size() + 1), selectedDoctor,
                    patient, date, appointmentTime);
            patient.addAppointment(appointment);
            selectedDoctor.addAppointment(appointment);
            System.out.println("Appointment scheduled: " + appointment);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void rescheduleAppointment(Scanner scanner, Patient patient) {
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
            System.out.print("Enter new date (yyyy-mm-dd): ");
            String newDate = scanner.nextLine();
            System.out.print("Enter new time (HH:mm): ");
            String newTime = scanner.nextLine();
            // Combine new date and time to create LocalDateTime
            LocalDateTime newAppointmentTime = LocalDateTime.parse(newDate + "T" + newTime); // Ensure this format is
                                                                                             // valid
            Appointment newAppointment = new Appointment(appointmentToReschedule.getAppointmentID(),
                    appointmentToReschedule.getDoctor(), patient, newDate, newAppointmentTime);
            patient.getAppointments().remove(appointmentToReschedule);
            patient.getAppointments().add(newAppointment);
            appointmentToReschedule.getDoctor().updateAppointment(appointmentToReschedule, newAppointment);
            System.out.println("Appointment rescheduled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void cancelAppointment(Scanner scanner, Patient patient) {
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
            appointmentToCancel.getDoctor().removeAppointment(appointmentToCancel);
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
                appointment.getOutcomeRecord().viewOutcome(); // This prints the outcome details
            }
        }
    }

    public void viewBillingDetails() {
        billing.viewBill(); // Display billing details
    }
}
