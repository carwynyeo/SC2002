package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Inventory.ReplenishmentRequest;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Pharmacist;
import com.hospitalmanagementsystem.MedicalRecord.Prescription;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.PharmacistRepository;
import com.hospitalmanagementsystem.Service.ChangePasswordService;
import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.Optional;
import java.util.Scanner;

public class PharmacistController extends UserController {
    private PharmacistRepository pharmacistRepository;
    private InventoryManager inventoryManager;
    private ReplenishmentRequest replenishmentRequest;
    private Logger logger;

    public PharmacistController(ChangePasswordService changePasswordService, PharmacistRepository pharmacistRepository,
                                InventoryManager inventoryManager, ReplenishmentRequest replenishmentrequest, Logger logger) {
        super(changePasswordService);
        this.pharmacistRepository = pharmacistRepository;
        this.inventoryManager = inventoryManager;
        this.replenishmentRequest = replenishmentrequest;
        this.logger = logger;
    }

    public Optional<User> loginPharmacist(Scanner scanner) {
        System.out.print("Enter Pharmacist ID: ");
        String pharmacistId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pharmacistPassword = scanner.nextLine();

        Pharmacist pharmacist = pharmacistRepository.findPharmacistById(pharmacistId);
        if (pharmacist != null && pharmacist.getPassword().equals(pharmacistPassword)) {
            return Optional.of(pharmacist);
        }
        return Optional.empty();
    }

    public void viewAppointmentOutcomeRecord(Scanner scanner) {
        System.out.println("Enter Appointment ID to view outcome record:");
        String appointmentId = scanner.nextLine();
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null) {
            viewPrescription(appointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void viewPrescription(Appointment appointment) {
        Prescription prescription = appointment.getPrescription();
        if (prescription != null) {
            System.out.println("Prescription for appointment " + appointment.getAppointmentID() + ": " + appointment.getPrescription());
        } else {
            System.out.println("No prescription found for this appointment.");
        }
    }

    public void updatePrescriptionStatus(Scanner scanner) {
        System.out.println("Enter Appointment ID to update prescription status:");
        String appointmentId = scanner.nextLine();
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null) {
            Prescription prescription = appointment.getPrescription();
            if (prescription != null) {
                System.out.print("Enter new prescription status (e.g., Prepared, Dispensed): ");
                String newStatus = scanner.nextLine();
                prescription.setStatus(newStatus);
                System.out.println("Prescription status updated for appointment " + appointment.getAppointmentID() + ": " + newStatus);
            } else {
                System.out.println("No prescription found for this appointment.");
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }


    public void prepareMedication(Appointment appointment) {
        Prescription prescription = appointment.getPrescription();
        if (prescription != null && prescription.getStatus().equals("Pending")) {
            prescription.setStatus("Prepared");
            System.out.println("Medication prepared for appointment " + appointment.getAppointmentID());
        } else if (prescription == null) {
            System.out.println("No prescription found for this appointment.");
        } else {
            System.out.println("Prescription already prepared or dispensed.");
        }
    }


    public void viewMedicationInventory() {
        System.out.println("Inventory: " + inventoryManager.getInventoryList());
    }

    public void submitReplenishmentRequest(Scanner scanner) {
        System.out.print("Enter medication name for replenishment: ");
        String medication = scanner.nextLine();
        replenishmentRequest.submitRequest(new ReplenishmentRequest());
        System.out.println("Replenishment request submitted for " + medication);
        logger.logInfo("Replenishment request submitted for " + medication);
    }

    private Appointment findAppointmentById(String appointmentId) {
        // Assume this method interacts with the repository to retrieve an appointment by ID
        return pharmacistRepository.findAppointmentById(appointmentId);
    }

    @Override
    public void createUserAccount(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        String defaultPassword = "password";
        // Create new pharmacist if ID is unique
        Pharmacist newPharmacist = new Pharmacist(id, name, defaultPassword, new InventoryManager());
        pharmacistRepository.addPharmacist(newPharmacist); // Add to repository
        System.out.println("Pharmacist account created successfully!");
    }
}

// public void updatePrescriptionStatus(Appointment appointment, Scanner scanner) {
//        System.out.print("Enter new prescription status: ");
//        String status = scanner.nextLine();
//        appointment.getPrescription().setStatus(status);
//        System.out.println("Prescription status updated.");
//        logger.logInfo("Updated prescription status for appointment: " + appointment.getAppointmentID());
//    }