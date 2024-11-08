package com.hospitalmanagementsystem.Boundary;

import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Controller.DoctorController;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;

import java.util.List;
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
        List<Patient> patients = doctorController.getPatients(currentDoctor.getId()); // Get actual patient list from the controller

        String appointmentId;  // Declared outside switch to be accessible across cases
        Appointment appointment;  // Declared outside switch to be accessible across cases

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
                case 1:
                    doctorController.viewPatientMedicalRecord(patients, scanner);
                    break;
                case 2:
                    doctorController.updatePatientMedicalRecord(patients, scanner);
                    break;
                case 3:
                    doctorController.viewPersonalSchedule(currentDoctor);
                    break;
                case 4:
                    System.out.print("Enter date for availability: ");
                    String date = scanner.nextLine();
                    String slotId = "slot-" + System.currentTimeMillis(); // Generate a unique slotId (you can customize this)
                    TimeSlot slot = new TimeSlot(slotId, date, "09:00", "17:00");
                    doctorController.setAvailability(currentDoctor, slot);
                    break;

                case 5:
                    System.out.print("Enter Appointment ID to manage: ");
                    appointmentId = scanner.nextLine();
                    appointment = doctorController.findAppointmentById(appointmentId); // Assume helper method
                    if (appointment != null) {
                        System.out.println("1. Accept Appointment\n2. Decline Appointment");
                        int decision = scanner.nextInt();
                        doctorController.manageAppointmentRequest(currentDoctor, appointment, decision == 1);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 6:
                    //Fix this
                    doctorController.viewPersonalSchedule(currentDoctor);
                    break;
                case 7:
                    System.out.print("Enter Appointment ID to record outcome: ");
                    appointmentId = scanner.nextLine();
                    appointment = doctorController.findAppointmentById(appointmentId); // Assume helper method
                    if (appointment != null) {
                        System.out.print("Enter outcome: ");
                        String outcome = scanner.nextLine();
                        doctorController.recordAppointmentOutcome(currentDoctor, appointment, outcome);
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 8:
                    doctorController.changePassword(currentDoctor,scanner);
                    break;
                case 9:
                    System.out.println("Logging out...");
                    doctorController.logout(currentDoctor);
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
