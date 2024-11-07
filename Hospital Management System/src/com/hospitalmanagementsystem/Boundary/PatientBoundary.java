package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.PatientController;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientBoundary extends UserBoundary {
    private final PatientController patientController;
    private final Patient currentPatient;

    public PatientBoundary(PatientController patientController, Patient currentPatient) {
        super(currentPatient.getId(), currentPatient.getName(), currentPatient.getPassword(), currentPatient.getRole());
        this.patientController = patientController;
        this.currentPatient = currentPatient;
    }
    @Override
    public void showMenu(Scanner scanner) {
        List<Doctor> doctors = new ArrayList<>(); // Placeholder for actual doctor list (can be passed in)

        while (true) {
            System.out.println("Patient Menu:");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcomes");
            System.out.println("9. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    patientController.viewMedicalRecord();
                    break;
                case 2:
                    patientController.updatePersonalInformation(scanner);
                    break;
                case 3:
                    patientController.viewAvailableAppointments(doctors);
                    break;
                case 4:
                    patientController.scheduleAppointment(scanner, doctors);
                    break;
                case 5:
                    patientController.rescheduleAppointment(scanner);
                    break;
                case 6:
                    patientController.cancelAppointment(scanner);
                    break;
                case 7:
                    patientController.viewScheduledAppointments();
                    break;
                case 8:
                    patientController.viewPastAppointmentOutcomes();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
