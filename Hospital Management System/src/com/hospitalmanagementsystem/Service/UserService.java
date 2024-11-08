package com.hospitalmanagementsystem.Service;

import com.hospitalmanagementsystem.Controller.*;
import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Repository.AdministratorRepository;
import com.hospitalmanagementsystem.Repository.DoctorRepository;
import com.hospitalmanagementsystem.Repository.PatientRepository;
import com.hospitalmanagementsystem.Repository.PharmacistRepository;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;

/*** Used for Account Creation and accessing each user's specific login function
 *
 */
public class UserService {
    private final PatientController patientController;
    private final DoctorController doctorController;
    private final AdministratorController adminController;
    private final PharmacistController pharmacistController;

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PharmacistRepository pharmacistRepository;
    private final AdministratorRepository adminRepository;

    public UserService(PatientController patientController, DoctorController doctorController,
                       AdministratorController adminController, PharmacistController pharmacistController,
                       PatientRepository patientRepository, DoctorRepository doctorRepository,
                       PharmacistRepository pharmacistRepository, AdministratorRepository adminRepository) {
        this.patientController = patientController;
        this.doctorController = doctorController;
        this.adminController = adminController;
        this.pharmacistController = pharmacistController;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.adminRepository = adminRepository;
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
        String defaultPassword = "password"; // Default password for all users

        switch (roleChoice) {
            case 1 -> {
                // Check if patient with same ID already exists
                if (patientRepository.findPatientById(id) != null) {
                    System.out.println("A Patient with this ID already exists.");
                    return;
                }
                // Create new patient if ID is unique
                Patient newPatient = new Patient(id, name, defaultPassword, new MedicalRecord(id, name, "O+"));
                patients.add(newPatient);
                patientRepository.addPatient(newPatient); // Add to repository
                System.out.println("Patient account created successfully!");
            }
            case 2 -> {
                // Check if doctor with same ID already exists
                if (doctorRepository.findDoctorById(id) != null) {
                    System.out.println("A Doctor with this ID already exists.");
                    return;
                }
                // Create new doctor if ID is unique
                Doctor newDoctor = new Doctor(id, name, defaultPassword);
                doctors.add(newDoctor);
                doctorRepository.addDoctor(newDoctor); // Add to repository
                System.out.println("Doctor account created successfully!");
            }
            case 3 -> {
                // Check if pharmacist with same ID already exists
                if (pharmacistRepository.findPharmacistById(id) != null) {
                    System.out.println("A Pharmacist with this ID already exists.");
                    return;
                }
                // Create new pharmacist if ID is unique
                Pharmacist newPharmacist = new Pharmacist(id, name, defaultPassword, new InventoryManager());
                pharmacists.add(newPharmacist);
                pharmacistRepository.addPharmacist(newPharmacist); // Add to repository
                System.out.println("Pharmacist account created successfully!");
            }
            case 4 -> {
                // Check if administrator with same ID already exists
                if (adminRepository.findAdministratorById(id) != null) {
                    System.out.println("An Administrator with this ID already exists.");
                    return;
                }
                // Create new administrator if ID is unique
                Administrator newAdmin = new Administrator(id, name, defaultPassword);
                admins.add(newAdmin);
                adminRepository.addAdministrator(newAdmin); // Add to repository
                System.out.println("Administrator account created successfully!");
            }
            default -> System.out.println("Invalid choice. Account creation failed.");
        }
    }

    public Optional<User> loginUser(int choice, Scanner scanner) {
        switch (choice) {
            case 2 -> {
                return patientController.loginPatient(scanner);
            }
            case 3 -> {
                return doctorController.loginDoctor(scanner);
            }
            case 4 -> {
                return pharmacistController.loginPharmacist(scanner);
            }
            case 5 -> {
                return adminController.loginAdministrator(scanner);
            }
            default -> {
                System.out.println("Invalid login choice.");
                return Optional.empty();
            }
        }
    }

}
