package com.hospitalmanagementsystem;

import com.hospitalmanagementsystem.Boundary.*;
import com.hospitalmanagementsystem.Service.ChangePasswordService;
import com.hospitalmanagementsystem.Controller.*;
import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Repository.*;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord; // Import MedicalRecord for testing

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final PatientRepository patientRepository = new PatientRepository();
    private final DoctorRepository doctorRepository = new DoctorRepository();
    private final AdministratorRepository adminRepository = new AdministratorRepository();
    private final PharmacistRepository pharmacistRepository = new PharmacistRepository();
    // Add MedicalRecordRepository for testing
    private final MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

    // Separate ChangePasswordService instances with specific credentials for each
    // role
    private final ChangePasswordService patientAuthService = new ChangePasswordService("patientPassword");
    private final ChangePasswordService doctorAuthService = new ChangePasswordService("doctorPassword");
    private final ChangePasswordService adminAuthService = new ChangePasswordService("adminPassword");
    private final ChangePasswordService pharmacistAuthService = new ChangePasswordService("pharmacistPassword");

    private final PatientController patientController = new PatientController(patientAuthService, patientRepository);
    private final DoctorController doctorController = new DoctorController(doctorAuthService, doctorRepository);
    private final AdministratorController adminController = new AdministratorController(adminAuthService,
            adminRepository);
    private final PharmacistController pharmacistController = new PharmacistController(pharmacistAuthService,
            pharmacistRepository, null, null, null);

    public static void main(String[] args) {
        new Main().start(); // Create an instance of Main and call the instance method start
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hospital Management System!");

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createUserAccount(scanner);
                case 2 -> loginAsPatient(scanner);
                case 3 -> loginAsDoctor(scanner);
                case 4 -> loginAsPharmacist(scanner);
                case 5 -> loginAsAdministrator(scanner);
                case 6 -> {
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                }
                case 7 -> manageMedicalRecords(scanner); // New option for managing medical records for testing
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("1. Create User Account");
        System.out.println("2. Login as Patient");
        System.out.println("3. Login as Doctor");
        System.out.println("4. Login as Pharmacist");
        System.out.println("5. Login as Administrator");
        System.out.println("6. Exit");
        System.out.println("7. Manage Medical Records"); // New menu option for testing
        System.out.print("Enter choice: ");
    }

    // New method to manage medical records for testing
    private void manageMedicalRecords(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Medical Record");
            System.out.println("2. Update Medical Record");
            System.out.println("3. Find Medical Record");
            System.out.println("4. Delete Medical Record");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addMedicalRecord(scanner);
                case 2 -> updateMedicalRecord(scanner);
                case 3 -> findMedicalRecord(scanner);
                case 4 -> deleteMedicalRecord(scanner);
                case 5 -> {
                    return; // Go back to the main menu
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addMedicalRecord(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Blood Type: ");
        String bloodType = scanner.nextLine();
        System.out.print("Enter Contact Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Contact Phone: ");
        String phone = scanner.nextLine();

        // Create a MedicalRecord with the new constructor
        MedicalRecord medicalRecord = new MedicalRecord(patientID, name, bloodType, email, phone);
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        System.out.println("Medical record added successfully.");
    }

    private void updateMedicalRecord(Scanner scanner) {
        System.out.print("Enter Patient ID to update: ");
        String patientID = scanner.nextLine();

        // Use findMedicalRecordById to retrieve the existing record
        Optional<MedicalRecord> existingRecordOptional = medicalRecordRepository.findMedicalRecordById(patientID);

        if (existingRecordOptional.isPresent()) {
            MedicalRecord existingRecord = existingRecordOptional.get();

            System.out.print("Enter new Patient Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                name = existingRecord.getName(); // Keep the existing name if blank
            }

            System.out.print("Enter new Blood Type (leave blank to keep current): ");
            String bloodType = scanner.nextLine();
            if (bloodType.isEmpty()) {
                bloodType = existingRecord.getBloodType(); // Keep the existing blood type if blank
            }

            System.out.print("Enter new Contact Email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (email.isEmpty()) {
                email = existingRecord.getContactInfo().getEmail(); // Keep the existing email if blank
            }

            System.out.print("Enter new Contact Phone (leave blank to keep current): ");
            String newPhone = scanner.nextLine();
            if (newPhone.isEmpty()) {
                newPhone = existingRecord.getContactInfo().getPhoneNumber();
            }

            // Create a new MedicalRecord with updated information
            MedicalRecord updatedRecord = new MedicalRecord(patientID, name, bloodType, email, newPhone);
            medicalRecordRepository.updateMedicalRecord(updatedRecord);
            System.out.println("Medical record updated successfully.");
        } else {
            System.out.println("Medical record not found.");
        }
    }

    private void findMedicalRecord(Scanner scanner) {
        System.out.print("Enter Patient ID to find: ");
        String patientID = scanner.nextLine();
        Optional<MedicalRecord> optionalRecord = medicalRecordRepository.findMedicalRecordById(patientID);

        if (optionalRecord.isPresent()) {
            MedicalRecord record = optionalRecord.get();
            System.out.println("Patient ID: " + record.getPatientID());
            System.out.println("Patient Name: " + record.getName());
            System.out.println("Blood Type: " + record.getBloodType());
        } else {
            System.out.println("Medical record not found for Patient ID: " + patientID);
        }
    }

    private void deleteMedicalRecord(Scanner scanner) {
        System.out.print("Enter Patient ID to delete: ");
        String patientID = scanner.nextLine();
        medicalRecordRepository.deleteMedicalRecord(patientID);
    }

    private void loginAsPatient(Scanner scanner) {
        Optional<User> loggedInUser = patientController.loginPatient(scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Patient patient) {
            // Pass entire Patient object and controller
            PatientBoundary patientBoundary = new PatientBoundary(patientController, patient);
            patientBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Patient credentials.");
        }
    }

    private void loginAsDoctor(Scanner scanner) {
        Optional<User> loggedInUser = doctorController.loginDoctor(scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Doctor doctor) {
            DoctorBoundary doctorBoundary = new DoctorBoundary(doctorController, doctor);
            doctorBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Doctor credentials.");
        }
    }

    private void loginAsPharmacist(Scanner scanner) {
        Optional<User> loggedInUser = pharmacistController.loginPharmacist(scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Pharmacist pharmacist) {
            // Pass entire Pharmacist object and controller
            PharmacistBoundary pharmacistBoundary = new PharmacistBoundary(pharmacistController, pharmacist);
            pharmacistBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Pharmacist credentials.");
        }
    }

    private void loginAsAdministrator(Scanner scanner) {
        Optional<User> loggedInUser = adminController.loginAdministrator(scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Administrator admin) {
            // Pass entire Administrator object and controller
            AdministratorBoundary adminBoundary = new AdministratorBoundary(adminController, admin);
            adminBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Administrator credentials.");
        }
    }

    private void createUserAccount(Scanner scanner) {
        System.out.println("Choose a user role to create:");
        System.out.println("1. Patient\n2. Doctor\n3. Pharmacist\n4. Administrator");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (roleChoice) {
            case 1 ->
                patientController.createUserAccount(scanner);

            case 2 ->
                doctorController.createUserAccount(scanner);

            case 3 ->
                pharmacistController.createUserAccount(scanner);

            case 4 ->
                adminController.createUserAccount(scanner);

            default ->
                System.out.println("Invalid login choice.");

        }

    }
}