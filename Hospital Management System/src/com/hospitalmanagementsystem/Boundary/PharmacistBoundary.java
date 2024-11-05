package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.PharmacistController;
import com.hospitalmanagementsystem.Model.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PharmacistBoundary extends UserBoundary {

    private final PharmacistController pharmacistController;

    public PharmacistBoundary(String id, String name, String password, String role, PharmacistController pharmacistController) {
        super(id, name, password, role);// Call to UserBoundary constructor
        this.pharmacistController = pharmacistController;
    }

    @Override
    public void showMenu(Scanner scanner) {
        List<Appointment> appointments = pharmacistController.getAppointments(); // Placeholder for actual appointments

        while (true) {
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.println("Enter Appointment ID to view prescription: ");
                    String appointmentId = scanner.nextLine();
                    Appointment selectedAppointment = pharmacistController.findAppointmentById(appointmentId);
                    if (selectedAppointment != null) {
                        pharmacistController.viewPrescription(selectedAppointment);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter Appointment ID to update prescription status: ");
                    appointmentId = scanner.nextLine();
                    selectedAppointment = pharmacistController.findAppointmentById(appointmentId);
                    if (selectedAppointment != null) {
                        pharmacistController.updatePrescriptionStatus(selectedAppointment, scanner);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;

                case 3:
                    pharmacistController.viewMedicationInventory();
                    break;
                case 4:
                    pharmacistController.submitReplenishmentRequest(scanner);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
