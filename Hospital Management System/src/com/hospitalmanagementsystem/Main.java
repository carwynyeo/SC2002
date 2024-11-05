package com.hospitalmanagementsystem;

import com.hospitalmanagementsystem.Service.UserService;
import com.hospitalmanagementsystem.Controller.*;
import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final PatientRepository patientRepository = new PatientRepository();
    private final DoctorRepository doctorRepository = new DoctorRepository();
    private final AdministratorRepository adminRepository = new AdministratorRepository();
    private final PharmacistRepository pharmacistRepository = new PharmacistRepository();

    private final PatientController patientController = new PatientController(patientRepository);
    private final DoctorController doctorController = new DoctorController(doctorRepository);
    private final AdministratorController adminController = new AdministratorController(adminRepository);
    private final PharmacistController pharmacistController = new PharmacistController(pharmacistRepository);

    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Pharmacist> pharmacists = new ArrayList<>();
    private final List<Administrator> admins = new ArrayList<>();

    private final UserService userService = new UserService(patientController, doctorController, adminController, pharmacistController);

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
                case 1 -> userService.createUserAccount(scanner, patients, doctors, pharmacists, admins);
                case 2, 3, 4, 5 -> userService.loginUser(choice, scanner, patients, doctors, pharmacists, admins);
                case 6 -> {
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                }
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
        System.out.print("Enter choice: ");
    }
}
