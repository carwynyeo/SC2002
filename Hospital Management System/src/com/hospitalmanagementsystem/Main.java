package com.hospitalmanagementsystem;

import com.hospitalmanagementsystem.Boundary.*;
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

    private final PatientController patientController = new PatientController(patientRepository);
    private final DoctorController doctorController = new DoctorController(doctorRepository);
    private final AdministratorController adminController = new AdministratorController(adminRepository);
    private final PharmacistController pharmacistController = new PharmacistController(pharmacistRepository);

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
        Optional<User> loggedInUser = userService.loginUser(2, scanner, patients, doctors, pharmacists, admins);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Patient patient) {
            PatientBoundary patientBoundary = new PatientBoundary(patient.getId(), patient.getName(), patient.getPassword(), "Patient", patientController);
            patientBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Patient credentials.");
        }
    }

    private void loginAsDoctor(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(3, scanner, patients, doctors, pharmacists, admins);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Doctor doctor) {
            DoctorBoundary doctorBoundary = new DoctorBoundary(doctor.getId(), doctor.getName(), doctor.getPassword(), "Doctor", doctorController);
            doctorBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Doctor credentials.");
        }
    }

    private void loginAsPharmacist(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(4, scanner, patients, doctors, pharmacists, admins);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Pharmacist pharmacist) {
            PharmacistBoundary pharmacistBoundary = new PharmacistBoundary(pharmacist.getId(), pharmacist.getName(), pharmacist.getPassword(), "Pharmacist", pharmacistController);
            pharmacistBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Pharmacist credentials.");
        }
    }

    private void loginAsAdministrator(Scanner scanner) {
        Optional<User> loggedInUser = userService.loginUser(5, scanner, patients, doctors, pharmacists, admins);
        if (loggedInUser.isPresent() && loggedInUser.get() instanceof Administrator admin) {
            AdministratorBoundary adminBoundary = new AdministratorBoundary(admin.getId(), admin.getName(), admin.getPassword(), "Administrator", adminController);
            adminBoundary.showMenu(scanner);
        } else {
            System.out.println("Invalid Administrator credentials.");
        }
    }
}