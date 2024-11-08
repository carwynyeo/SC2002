package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.PharmacistController;
import com.hospitalmanagementsystem.Model.Pharmacist;

import java.util.Scanner;

public class PharmacistBoundary extends UserBoundary {
    private final PharmacistController pharmacistController;
    private final Pharmacist currentPharmacist;

    public PharmacistBoundary(PharmacistController pharmacistController, Pharmacist currentPharmacist) {
        super(currentPharmacist.getId(), currentPharmacist.getName(), currentPharmacist.getPassword(), currentPharmacist.getRole());
        this.pharmacistController = pharmacistController;
        this.currentPharmacist = currentPharmacist;
    }

    @Override
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Settings"); // So far only has change password function
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1 -> pharmacistController.viewAppointmentOutcomeRecord(scanner);
                case 2 -> pharmacistController.updatePrescriptionStatus(scanner);
                case 3 -> pharmacistController.viewMedicationInventory();
                case 4 -> pharmacistController.submitReplenishmentRequest(scanner);
                case 5 -> pharmacistController.changePassword(currentPharmacist, scanner);
                case 6 -> {
                    System.out.println("Logging out...");
                    pharmacistController.logout(currentPharmacist);
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
