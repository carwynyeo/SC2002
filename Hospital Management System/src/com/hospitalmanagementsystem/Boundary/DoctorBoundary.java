package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Controller.DoctorController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorBoundary extends UserBoundary {

    private final DoctorController doctorController;

    public DoctorBoundary(String id, String name, String password, String role, DoctorController doctorController) {
        super(id, name, password, role); // Call to UserBoundary constructor
        this.doctorController = doctorController;
    }

    @Override
    public void showMenu(Scanner scanner) {
        List<Patient> patients = doctorController.getPatients(); // Get actual patient list from the controller
        List<Appointment> appointments = doctorController.getAppointments(); // Get actual appointment list from the controller

        while (true) {
            System.out.println("Doctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    doctorController.viewPatientMedicalRecord(patients, scanner);
                    break;
                case 2:
                    doctorController.updatePatientMedicalRecord(patients, scanner);
                    break;
                case 3:
                    doctorController.viewPersonalSchedule();
                    break;
                case 4:
                    System.out.print("Enter availability date and time: ");
                    String dateTime = scanner.nextLine();
                    doctorController.setAvailability(dateTime);
                    break;
                case 5:
                    doctorController.viewPersonalSchedule();
                    System.out.println("Enter Appointment ID to accept/decline: ");
                    String appointmentId = scanner.nextLine();
                    Appointment selectedAppointment = null;
                    for (Appointment appointment : appointments) {
                        if (appointment.getAppointmentID().equals(appointmentId)) {
                            selectedAppointment = appointment;
                            break;
                        }
                    }
                    if (selectedAppointment != null) {
                        System.out.println("1. Accept Appointment\n2. Decline Appointment");
                        int decision = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        if (decision == 1) {
                            doctorController.acceptAppointment(selectedAppointment);
                        } else {
                            doctorController.declineAppointment(selectedAppointment);
                        }
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 6:
                    doctorController.viewPersonalSchedule();
                    break;
                case 7:
                    System.out.println("Enter Appointment ID to record outcome: ");
                    appointmentId = scanner.nextLine();
                    selectedAppointment = null;
                    for (Appointment appointment : appointments) {
                        if (appointment.getAppointmentID().equals(appointmentId)) {
                            selectedAppointment = appointment;
                            break;
                        }
                    }
                    if (selectedAppointment != null) {
                        doctorController.recordAppointmentOutcome(selectedAppointment, scanner);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
