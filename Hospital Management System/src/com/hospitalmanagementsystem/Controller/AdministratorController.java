package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Utility.Logger;
import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.Repository.AdministratorRepository;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdministratorController {
    private final AdministratorRepository adminRepository;
    private final InventoryManager inventoryManager = new InventoryManager();
    private final Logger logger = new Logger();

    public AdministratorController(AdministratorRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<User> loginAdministrator(Scanner scanner) {
        System.out.print("Enter Administrator ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String adminPassword = scanner.nextLine();

        Administrator admin = adminRepository.findAdministratorById(adminId);
        if (admin != null && admin.getPassword().equals(adminPassword)) {
            return Optional.of(admin);
        }
        return Optional.empty();
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

        switch (choice) {
            case 1 -> viewStaff();
            case 2 -> addStaff(scanner);
            case 3 -> removeStaff(scanner);
            default -> System.out.println("Invalid choice.");
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

        User newStaff = switch (role.toLowerCase()) {
            case "doctor" -> new Doctor(id, name, password);
            case "pharmacist" -> new Pharmacist(id, name, password, inventoryManager);
            default -> {
                System.out.println("Invalid role.");
                yield null;
            }
        };

        if (newStaff != null) {
            adminRepository.addStaff(newStaff);
            System.out.println("Staff added: " + name);
            logger.logInfo("New staff added: " + name);
        }
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

    public void viewAppointments() {
        List<Appointment> appointments = adminRepository.getAppointments(); // Ensure getAppointments() method exists in the repository.
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

        switch (choice) {
            case 1 -> System.out.println(inventoryManager.getInventoryList());
            case 2 -> {
                System.out.print("Enter medication name: ");
                String medication = scanner.nextLine();
                System.out.print("Enter stock quantity: ");
                int quantity = scanner.nextInt();
                inventoryManager.updateStock(medication, quantity);
                System.out.println("Inventory updated.");
                logger.logInfo("Inventory updated for " + medication);
            }
            default -> System.out.println("Invalid option.");
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
