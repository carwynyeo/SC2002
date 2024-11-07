package com.hospitalmanagementsystem;

import com.hospitalmanagementsystem.Boundary.*;
import com.hospitalmanagementsystem.Service.ChangePasswordService;
import com.hospitalmanagementsystem.Service.UserService;
import com.hospitalmanagementsystem.Controller.*;
import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Repository.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final PatientRepository patientRepository = new PatientRepository();
    private final DoctorRepository doctorRepository = new DoctorRepository();
    private final AdministratorRepository adminRepository = new AdministratorRepository();
    private final PharmacistRepository pharmacistRepository = new PharmacistRepository();

    // Separate ChangePasswordService instances with specific credentials for each role
    private final ChangePasswordService patientAuthService = new ChangePasswordService("patientPassword");
    private final ChangePasswordService doctorAuthService = new ChangePasswordService("doctorPassword");
    private final ChangePasswordService adminAuthService = new ChangePasswordService("adminPassword");
    private final ChangePasswordService pharmacistAuthService = new ChangePasswordService("pharmacistPassword");

    private final PatientController patientController = new PatientController(patientAuthService, patientRepository);
    private final DoctorController doctorController = new DoctorController(doctorAuthService, doctorRepository);
    private final AdministratorController adminController = new AdministratorController(adminAuthService, adminRepository);
    private final PharmacistController pharmacistController = new PharmacistController(pharmacistAuthService, pharmacistRepository);

    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Pharmacist> pharmacists = new ArrayList<>();
    private final List<Administrator> admins = new ArrayList<>();

    UserService userService = new UserService(patientController, doctorController, adminController,
            pharmacistController, patientRepository, doctorRepository, pharmacistRepository, adminRepository
    );

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
                case 2 -> loginAsPatient(scanner);
                case 3 -> loginAsDoctor(scanner);
                case 4 -> loginAsPharmacist(scanner);
                case 5 -> loginAsAdministrator(scanner);
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

    private void loginAsPatient(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(2, scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Patient patient) {
            PatientBoundary patientBoundary = new PatientBoundary(patientController, patient); // Pass entire Patient object and controller
            patientBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Patient credentials.");
        }
    }

    private void loginAsDoctor(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(3, scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Doctor doctor) {
            DoctorBoundary doctorBoundary = new DoctorBoundary(doctorController, doctor);
            doctorBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Doctor credentials.");
        }
    }


    private void loginAsPharmacist(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(4, scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Pharmacist pharmacist) {
            PharmacistBoundary pharmacistBoundary = new PharmacistBoundary(pharmacistController, pharmacist); // Pass entire Pharmacist object and controller
            pharmacistBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Pharmacist credentials.");
        }
    }

    private void loginAsAdministrator(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(5, scanner);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Administrator admin) {
            AdministratorBoundary adminBoundary = new AdministratorBoundary(adminController, admin); // Pass entire Administrator object and controller
            adminBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Administrator credentials.");
        }
    }
}