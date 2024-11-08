package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.PatientController;
import com.hospitalmanagementsystem.Model.Patient;

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
            System.out.println("9. Settings"); // So far only has change password function
            System.out.println("10. View Billing Details");
            System.out.println("11. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1 -> patientController.viewMedicalRecord(currentPatient);
                case 2 -> patientController.updatePersonalInformation(currentPatient,scanner);
                case 3 -> patientController.viewAvailableAppointments(currentPatient,scanner);
                case 4 -> patientController.scheduleAppointment(currentPatient,scanner);
                case 5 -> patientController.rescheduleAppointment(currentPatient,scanner);
                case 6 -> patientController.cancelAppointment(currentPatient,scanner);
                case 7 -> patientController.viewScheduledAppointments(currentPatient);
                case 8 -> patientController.viewPastAppointmentOutcomes(currentPatient);
                case 9 -> patientController.changePassword(currentPatient, scanner);
                case 10 -> patientController.viewBillingDetails(currentPatient);
                case 11 -> {
                    System.out.println("Logging out...");
                    patientController.logout(currentPatient);
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
