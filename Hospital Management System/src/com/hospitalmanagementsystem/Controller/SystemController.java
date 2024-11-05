package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.Service.ValidationService;

import java.util.List;
import java.util.Scanner;

public class SystemController {
    private Logger logger;
    private ValidationService validationService;
    private InventoryManager inventoryManager;
    private Scanner scanner;

    public SystemController() {
        this.logger = new Logger();
        this.validationService = new ValidationService();
        this.inventoryManager = new InventoryManager();
        this.scanner = new Scanner(System.in);
    }

    // Manage user actions based on action input
    public void handleUserActions(String action, List<Appointment> existingAppointments, Appointment newAppointment) {
        switch (action.toLowerCase()) {
            case "schedule appointment":
                if (validationService.validateAppointment(newAppointment)) {
                    if (!checkForConflicts(newAppointment, existingAppointments)) {
                        existingAppointments.add(newAppointment);
                        System.out.println("Appointment scheduled successfully.");
                        logger.logInfo("Appointment scheduled for Patient: " + newAppointment.getPatient().getName());
                    } else {
                        System.out.println("Appointment conflict detected. Please reschedule.");
                        logger.logError("Appointment conflict for Doctor: " + newAppointment.getDoctor().getName());
                    }
                } else {
                    System.out.println("Invalid appointment details.");
                    logger.logError("Failed appointment validation for Patient: " + newAppointment.getPatient().getName());
                }
                break;

            case "cancel appointment":
                if (existingAppointments.contains(newAppointment)) {
                    existingAppointments.remove(newAppointment);
                    System.out.println("Appointment canceled successfully.");
                    logger.logInfo("Appointment canceled for Patient: " + newAppointment.getPatient().getName());
                } else {
                    System.out.println("Appointment not found.");
                    logger.logError("Failed to cancel appointment - not found.");
                }
                break;

            case "view inventory":
                viewInventory();
                break;

            case "update inventory":
                updateInventory();
                break;

            default:
                System.out.println("Invalid action. Please choose a valid operation.");
                logger.logError("Invalid action selected: " + action);
                break;
        }
    }

    // Method to check for conflicts in appointments
    private boolean checkForConflicts(Appointment appointment, List<Appointment> existingAppointments) {
        return new AppointmentConflictChecker().checkForConflicts(appointment, existingAppointments);
    }

    // View inventory using InventoryManager
    private void viewInventory() {
        System.out.println("Inventory List:");
        inventoryManager.getInventoryList().forEach((medicine, quantity) ->
                System.out.println(medicine + ": " + quantity + " units available"));
        logger.logInfo("Inventory viewed.");
    }

    // Update inventory by adding or updating stock
    private void updateInventory() {
        System.out.print("Enter the name of the medicine: ");
        String medicine = scanner.nextLine();
        System.out.print("Enter the quantity to add/update: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (validationService.validateMedicationName(medicine)) {
            if (inventoryManager.getInventoryList().containsKey(medicine)) {
                // Update stock if medicine exists
                inventoryManager.updateStock(medicine, quantity);
                logger.logInfo("Inventory updated for " + medicine);
            } else {
                // Add new medicine if it doesn't exist
                inventoryManager.addMedicine(medicine, quantity);
                logger.logInfo("New medicine added to inventory: " + medicine);
            }
        } else {
            System.out.println("Invalid medicine name.");
            logger.logError("Invalid medicine name entered.");
        }
    }

    // Method to manage appointments
    public void manageAppointments(Appointment appointment, List<Appointment> existingAppointments) {
        if (validationService.validateAppointment(appointment)) {
            if (!checkForConflicts(appointment, existingAppointments)) {
                existingAppointments.add(appointment);
                System.out.println("Appointment scheduled successfully.");
                logger.logInfo("Appointment scheduled.");
            } else {
                System.out.println("Appointment conflict detected.");
                resolveConflict(appointment, existingAppointments.get(0)); // Simplified resolution
            }
        } else {
            logger.logError("Invalid appointment details.");
            System.out.println("Invalid appointment details.");
        }
    }

    // Resolve appointment conflicts
    private void resolveConflict(Appointment newAppointment, Appointment conflictingAppointment) {
        new AppointmentConflictChecker().resolveConflict(conflictingAppointment, newAppointment);
    }

    // Log specific user actions
    public void logAction(String message) {
        logger.logInfo(message);
    }

    // Manage inventory directly
    public void manageInventory() {
        logger.logInfo("Managing inventory...");
        inventoryManager.trackLowStock();
    }
}
