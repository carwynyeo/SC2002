package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Controller.PatientController;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Patient;

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
            System.out.println("9. Settings"); //So far only has change password function
            System.out.println("10. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    patientController.viewMedicalRecord(currentPatient);
                    break;
                case 2:
                    patientController.updatePersonalInformation(scanner, currentPatient);
                    break;
                case 3:
                    patientController.viewAvailableAppointments(scanner);
                    break;
                case 4:
                    patientController.scheduleAppointment(scanner, currentPatient);
                    break;
                case 5:
                    patientController.rescheduleAppointment(scanner, currentPatient);
                    break;
                case 6:
                    patientController.cancelAppointment(scanner, currentPatient);
                    break;
                case 7:
                    patientController.viewScheduledAppointments(currentPatient);
                    break;
                case 8:
                    patientController.viewPastAppointmentOutcomes(currentPatient);
                    break;
                case 9:
                    patientController.changePassword(currentPatient,scanner);
                    break;
                case 10:
                    System.out.println("Logging out...");
                    patientController.logout(currentPatient);
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
