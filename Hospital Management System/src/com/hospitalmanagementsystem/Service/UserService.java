package com.hospitalmanagementsystem.Service;

import com.hospitalmanagementsystem.Controller.*;
import com.hospitalmanagementsystem.Inventory.Inventory;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Model.*;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private final PatientController patientController;
    private final DoctorController doctorController;
    private final AdministratorController adminController;
    private final PharmacistController pharmacistController;

    public UserService(PatientController patientController, DoctorController doctorController,
                       AdministratorController adminController, PharmacistController pharmacistController) {
        this.patientController = patientController;
        this.doctorController = doctorController;
        this.adminController = adminController;
        this.pharmacistController = pharmacistController;
    }

    public void createUserAccount(Scanner scanner, List<Patient> patients, List<Doctor> doctors,
                                  List<Pharmacist> pharmacists, List<Administrator> admins) {
        System.out.println("Choose a user role to create:");
        System.out.println("1. Patient\n2. Doctor\n3. Pharmacist\n4. Administrator");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        switch (roleChoice) {
            case 1 -> {
                patients.add(new Patient(id, name, password, new MedicalRecord(id, name, "O+")));
                System.out.println("Patient account created successfully!");
            }
            case 2 -> {
                doctors.add(new Doctor(id, name, password));
                System.out.println("Doctor account created successfully!");
            }
            case 3 -> {
                pharmacists.add(new Pharmacist(id, name, password, new Inventory()));
                System.out.println("Pharmacist account created successfully!");
            }
            case 4 -> {
                admins.add(new Administrator(id, name, password));
                System.out.println("Administrator account created successfully!");
            }
            default -> System.out.println("Invalid choice. Account creation failed.");
        }
    }

    public void loginUser(int choice, Scanner scanner, List<Patient> patients, List<Doctor> doctors,
                          List<Pharmacist> pharmacists, List<Administrator> admins) {
        switch (choice) {
            case 2 -> patientController.loginPatient(scanner, patients);
            case 3 -> doctorController.loginDoctor(scanner, doctors, patients);
            case 4 -> pharmacistController.loginPharmacist(scanner, pharmacists);
            case 5 -> adminController.loginAdministrator(scanner, admins);
            default -> System.out.println("Invalid login choice.");
        }
    }
}
