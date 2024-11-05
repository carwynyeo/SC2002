package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Repository.DoctorRepository;

import java.util.List;
import java.util.Scanner;

public class DoctorController {
    private DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void loginDoctor(Scanner scanner, List<Doctor> doctors, List<Patient> patients) {
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String doctorPassword = scanner.nextLine();

        for (Doctor doctor : doctors) {
            if (doctor.id.equals(doctorId) && doctor.password.equals(doctorPassword)) {
                doctor.login(doctorPassword);
                doctor.showMenu(scanner); // Pass the list of patients to the doctor’s menu
                return; // Exit the method after successful login
            }
        }
        System.out.println("Invalid Doctor ID or Password.");
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

    public void setAvailability(String dateTime) {
        TimeSlot timeSlot = new TimeSlot(schedule.getAvailableSlots().size() + 1, dateTime, "09:00", "17:00", true);
        schedule.addAvailableSlot(timeSlot);
        System.out.println("Availability slot added for: " + dateTime);
        logger.logInfo("Doctor set availability for: " + dateTime);
    }

  public void acceptAppointment(Appointment appointment) {
        appointment.setStatus("Confirmed");
        appointments.add(appointment);
        System.out.println("Appointment accepted: " + appointment);
        logger.logInfo("Accepted appointment for patient: " + appointment.getPatient().getName());
    }

    public void declineAppointment(Appointment appointment) {
        appointment.setStatus("Declined");
        System.out.println("Appointment declined: " + appointment);
        logger.logInfo("Declined appointment for patient: " + appointment.getPatient().getName());
    }

    public void recordAppointmentOutcome(Appointment appointment, Scanner scanner) {
        System.out.print("Enter appointment outcome: ");
        String outcome = scanner.nextLine();
        appointment.getOutcomeRecord().recordService("Service provided during the appointment");
        appointment.getOutcomeRecord().recordPrescription(appointment.getPrescription());
        appointment.setCompleted(true);
        System.out.println("Appointment outcome recorded: " + outcome);
        logger.logInfo("Recorded outcome for appointment: " + appointment.getAppointmentID());
    }

    public void viewPersonalSchedule() {
        System.out.println("Personal Schedule for " + name + ":");
        if (appointments.isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
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

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void updateAppointment(Appointment oldAppointment, Appointment newAppointment) {
        appointments.remove(oldAppointment);
        appointments.add(newAppointment);
    }

    public void cancelAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
}
