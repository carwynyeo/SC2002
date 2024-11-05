package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.AdministratorController;
import com.hospitalmanagementsystem.Controller.PatientController;
import com.hospitalmanagementsystem.Model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorBoundary extends UserBoundary {
    private final Scanner scanner = new Scanner(System.in);
    private final AdministratorController administratorController;

    public AdministratorBoundary(String id, String name, String password, String role, AdministratorController administratorController) {
        super(id, name, password, role); // Call the superclass constructor
        this.administratorController = administratorController;
    }
    @Override
    public void showMenu(Scanner scanner) {

        while (true) {
            System.out.println("Administrator Menu:");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    administratorController.manageStaff(scanner);
                    break;
                case 2:
                    administratorController.viewAppointments(appointments);
                    break;
                case 3:
                    administratorController.manageInventory(scanner);
                    break;
                case 4:
                    administratorController.approveReplenishmentRequest(scanner);
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
