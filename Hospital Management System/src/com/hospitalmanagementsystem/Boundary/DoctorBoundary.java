package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Controller.DoctorController;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;

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
                    doctorController.viewPersonalSchedule((Doctor) currentUser);
                    break;
                case 4:
                    System.out.print("Enter date for availability: ");
                    String date = scanner.nextLine();
                    TimeSlot slot = new TimeSlot(date, "09:00", "17:00");
                    doctorController.setAvailability((Doctor) currentUser, slot);
                    break;
                case 5:
                    System.out.print("Enter Appointment ID to manage: ");
                    String appointmentId = scanner.nextLine();
                    Appointment appointment = findAppointmentById(appointmentId); // Assume helper method
                    if (appointment != null) {
                        System.out.println("1. Accept Appointment\n2. Decline Appointment");
                        int decision = scanner.nextInt();
                        doctorController.manageAppointmentRequest((Doctor) currentUser, appointment, decision == 1);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 6:
                    doctorController.viewPersonalSchedule();
                    break;
                case 7:
                    System.out.print("Enter Appointment ID to record outcome: ");
                    appointmentId = scanner.nextLine();
                    appointment = findAppointmentById(appointmentId); // Assume helper method
                    if (appointment != null) {
                        System.out.print("Enter outcome: ");
                        String outcome = scanner.nextLine();
                        doctorController.recordAppointmentOutcome((Doctor) currentUser, appointment, outcome);
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
