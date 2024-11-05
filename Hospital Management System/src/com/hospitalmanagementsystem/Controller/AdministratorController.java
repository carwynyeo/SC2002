package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Boundary.AdministratorBoundary;
import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Repository.AdministratorRepository;
import java.util.List;
import java.util.Scanner;

public class AdministratorController {
    private final AdministratorRepository adminRepository;

    public AdministratorController(AdministratorRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void loginAdministrator(Scanner scanner, List<Administrator> admins) {
        System.out.print("Enter Administrator ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String adminPassword = scanner.nextLine();

        for (Administrator admin : admins) {
            if (admin.getId().equals(adminId) && admin.getPassword().equals(adminPassword)) {
                admin.showMenu(scanner);
                return; // Exit the method after successful login
            }
        }
        System.out.println("Invalid Administrator ID or Password.");
    }

    public void viewStaff() {
        List<User> staffList = adminRepository.getAllStaff();
        if (staffList.isEmpty()) {
            System.out.println("No staff found.");
        } else {
            System.out.println("Staff List:");
            for (User staff : staffList) {
                System.out.println(staff.getName() + " (ID: " + staff.getId() + ", Role: " + staff.getRole() + ")");
            }
        }
    }

    public void manageStaff(Scanner scanner) {
        System.out.println("1. View all staff\n2. Add new staff\n3. Remove staff");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            viewStaff();
        } else if (choice == 2) {
            addStaff(scanner);
        } else if (choice == 3) {
            removeStaff(scanner);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void addStaff(Scanner scanner) {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter staff role (Doctor/Pharmacist): ");
        String role = scanner.nextLine();
        System.out.print("Enter staff password: ");
        String password = scanner.nextLine();

        User newStaff;
        if (role.equalsIgnoreCase("Doctor")) {
            newStaff = new Doctor(id, name, password);
        } else if (role.equalsIgnoreCase("Pharmacist")) {
            newStaff = new Pharmacist(id, name, password, inventoryManager);
        } else {
            System.out.println("Invalid role.");
            return;
        }

        adminRepository.addStaff(newStaff);
        System.out.println("Staff added: " + name);
        logger.logInfo("New staff added: " + name);
    }

    private void removeStaff(Scanner scanner) {
        System.out.print("Enter staff ID to remove: ");
        String staffId = scanner.nextLine();
        if (adminRepository.removeStaffById(staffId)) {
            System.out.println("Staff removed.");
            logger.logInfo("Staff removed with ID: " + staffId);
        } else {
            System.out.println("Staff ID not found.");
        }
    }

    // View all appointments in the system
    public void viewAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    // Manage inventory by adding, removing, or updating stock
    public void manageInventory(Scanner scanner) {
        System.out.println("1. View Inventory\n2. Add/Update Stock");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            System.out.println(inventoryManager.getInventoryList());
        } else if (choice == 2) {
            System.out.print("Enter medication name: ");
            String medication = scanner.nextLine();
            System.out.print("Enter stock quantity: ");
            int quantity = scanner.nextInt();
            inventoryManager.updateStock(medication, quantity);
            System.out.println("Inventory updated.");
            logger.logInfo("Inventory updated for " + medication);
        } else {
            System.out.println("Invalid option.");
        }
    }

    // Approve replenishment requests
    public void approveReplenishmentRequest(Scanner scanner) {
        System.out.print("Enter medication name for approval: ");
        String medication = scanner.nextLine();
        inventoryManager.trackLowStock();  // Simulate tracking for replenishment
        System.out.println("Replenishment approved for " + medication);
        logger.logInfo("Replenishment approved for " + medication);
    }
}
