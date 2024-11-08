package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.AdministratorController;
import com.hospitalmanagementsystem.Model.Administrator;

import java.util.Scanner;

public class AdministratorBoundary extends UserBoundary {
    private final AdministratorController administratorController;
    private final Administrator currentAdministrator;

    public AdministratorBoundary(AdministratorController administratorController, Administrator currentAdministrator) {
        super(currentAdministrator.getId(), currentAdministrator.getName(), currentAdministrator.getPassword(), currentAdministrator.getRole());
        this.administratorController = administratorController;
        this.currentAdministrator = currentAdministrator;
    }

    @Override
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("Administrator Menu:");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Settings"); //So far only has change Password function
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1 -> administratorController.manageStaff(scanner);
                case 2 -> administratorController.viewAppointments();
                case 3 -> administratorController.manageInventory(scanner);
                case 4 -> administratorController.approveReplenishmentRequest(scanner);
                case 5 -> administratorController.changePassword(currentAdministrator,scanner);
                case 6 -> {
                    System.out.println("Logging out...");
                    administratorController.logout(currentAdministrator);
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
