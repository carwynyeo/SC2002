package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Controller.DoctorController;

import java.util.Scanner;

public class DoctorBoundary extends UserBoundary {

    private final DoctorController doctorController;
    private final Doctor currentDoctor;

    public DoctorBoundary(DoctorController doctorController, Doctor currentDoctor) {
        super(currentDoctor.getId(), currentDoctor.getName(), currentDoctor.getPassword(), currentDoctor.getRole()); // Use attributes from UserBoundary
        this.doctorController = doctorController;
        this.currentDoctor = currentDoctor;
    }

    @Override
    public void showMenu(Scanner scanner) {

        while (true) {
            System.out.println("Doctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Settings");  //So far only has change password function
            System.out.println("9. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1 -> doctorController.viewPatientMedicalRecord(currentDoctor, scanner);
                case 2 -> doctorController.updatePatientMedicalRecord(currentDoctor, scanner);
                case 3 -> doctorController.viewPersonalSchedule(currentDoctor);
                case 4 -> doctorController.setAvailability(currentDoctor, scanner);
                case 5 -> doctorController.manageAppointmentRequest(currentDoctor, scanner);
                case 6 -> doctorController.viewUpcomingAppointments(currentDoctor);
                case 7 -> doctorController.handleAppointmentOutcome(currentDoctor, scanner);
                case 8 -> doctorController.changePassword(currentDoctor, scanner);
                case 9 -> {
                    System.out.println("Logging out...");
                    doctorController.logout(currentDoctor);
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}