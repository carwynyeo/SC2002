package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Inventory.ReplenishmentRequest;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Pharmacist;
import com.hospitalmanagementsystem.MedicalRecord.Prescription;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.PharmacistRepository;
import com.hospitalmanagementsystem.Service.ChangePasswordService;

import java.util.Optional;
import java.util.Scanner;

public class PharmacistController extends UserController {
    private PharmacistRepository pharmacistRepository;

    public PharmacistController(ChangePasswordService changePasswordService, PharmacistRepository pharmacistRepository) {
        super(changePasswordService);
        this.pharmacistRepository = pharmacistRepository;
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



    public void viewPrescription(Appointment appointment) {
        Prescription prescription = appointment.getPrescription();
        if (prescription != null) {
            System.out.println("Prescription for appointment " + appointment.getAppointmentID() + ": " + appointment.getPrescription());
        } else {
            System.out.println("No prescription found for this appointment.");
        }
    }

    public void updatePrescriptionStatus(Appointment appointment, Scanner scanner) {
        Prescription prescription = appointment.getPrescription();
        if (prescription != null) {
            System.out.print("Enter new prescription status (e.g., Prepared, Dispensed): ");
            String newStatus = scanner.nextLine();
            prescription.setStatus(newStatus);
            System.out.println("Prescription status updated for appointment " + appointment.getAppointmentID() + ": " + newStatus);
        } else {
            System.out.println("No prescription found for this appointment.");
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
        inventoryManager.addReplenishmentRequest(new ReplenishmentRequest(medication, id));
        System.out.println("Replenishment request submitted for " + medication);
        logger.logInfo("Replenishment request submitted for " + medication);
    }
}

// public void updatePrescriptionStatus(Appointment appointment, Scanner scanner) {
//        System.out.print("Enter new prescription status: ");
//        String status = scanner.nextLine();
//        appointment.getPrescription().setStatus(status);
//        System.out.println("Prescription status updated.");
//        logger.logInfo("Updated prescription status for appointment: " + appointment.getAppointmentID());
//    }