package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.Doctor;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.Patient;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.DoctorRepository;
import com.hospitalmanagementsystem.Scheduling.AppointmentConflictChecker;
import com.hospitalmanagementsystem.Scheduling.TimeSlot;
import com.hospitalmanagementsystem.Service.ChangePasswordService;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DoctorController extends UserController {
    private final DoctorRepository doctorRepository;
    private final AppointmentConflictChecker conflictChecker = new AppointmentConflictChecker();
    private final Logger logger = new Logger();

    public DoctorController(ChangePasswordService changePasswordService, DoctorRepository doctorRepository) {
        super(changePasswordService);
        this.doctorRepository = doctorRepository;
    }

    public Optional<User> loginDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String doctorPassword = scanner.nextLine();

        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor != null && doctor.getPassword().equals(doctorPassword)) {
            return Optional.of(doctor);
        }
        return Optional.empty();
    }


    public void viewPatientMedicalRecord(Doctor doctor, Scanner scanner) {
        List<Patient> patients = doctorRepository.getPatientsByDoctorId(doctor.getId());
        System.out.print("Enter Patient ID to view medical records: ");
        String patientId = scanner.nextLine();

        // Find the patient by their ID
        Patient selectedPatient = null;
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                selectedPatient = patient;
                break;
            }
        }

        // Display the patient's medical record if found
        if (selectedPatient != null) {
            System.out.println("Medical Record for " + selectedPatient.getName() + ":");
            System.out.println(selectedPatient.getMedicalRecord().getRecordDetails());
            logger.logInfo("Doctor viewed medical record for patient: " + selectedPatient.getName());
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void updatePatientMedicalRecord(Doctor doctor, Scanner scanner) {
        List<Patient> patients = doctorRepository.getPatientsByDoctorId(doctor.getId());
        System.out.print("Enter Patient ID to update medical records: ");
        String patientId = scanner.nextLine();

        Patient selectedPatient = null;
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                selectedPatient = patient;
                break;
            }
        }

        if (selectedPatient != null) {
            System.out.print("Enter new diagnosis: ");
            String diagnosis = scanner.nextLine();
            System.out.print("Enter new treatment: ");
            String treatment = scanner.nextLine();

            selectedPatient.getMedicalRecord().addDiagnosis(diagnosis);
            selectedPatient.getMedicalRecord().addTreatment(treatment);
            System.out.println("Patient medical record updated.");
            logger.logInfo("Updated medical record for patient: " + selectedPatient.getName());
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void viewPersonalSchedule(Doctor doctor) {
        System.out.println("Personal Schedule for " + doctor.getName() + ":");
        if (doctor.getAppointments().isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            doctor.getAppointments().forEach(System.out::println);
        }
    }

    public void setAvailability(Doctor doctor, Scanner scanner) {
        System.out.print("Enter date for availability: ");
        String date = scanner.nextLine();
        TimeSlot slot = new TimeSlot("slot-" + System.currentTimeMillis(), date, "09:00", "17:00");
        doctor.addAvailableSlot(slot);
        logger.logInfo("Doctor " + doctor.getName() + " set availability for " + slot.getDate());
    }

    public void manageAppointmentRequest(Doctor doctor, Scanner scanner) {
        System.out.print("Enter Appointment ID to manage: ");
        String appointmentId = scanner.nextLine();
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment != null) {
            System.out.println("1. Accept Appointment\n2. Decline Appointment");
            int decision = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (decision == 1) {
                appointment.setStatus("Confirmed");
                doctor.addAppointment(appointment); //Doctor will create an appointment upon accepting
                logger.logInfo("Doctor " + doctor.getName() + " accepted appointment for patient: " + appointment.getPatient().getName());
            } else {
                appointment.setStatus("Declined");
                logger.logInfo("Doctor " + doctor.getName() + " declined appointment for patient: " + appointment.getPatient().getName());
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // Method to view appointments for a specific doctor
    public void viewUpcomingAppointments(Doctor doctor) {
        System.out.println("Upcoming appointments for " + doctor.getName() + ":");
        if (doctor.getAppointments().isEmpty()) {
            System.out.println("No scheduled upcoming appointments.");
        } else {
            doctor.getAppointments().forEach(System.out::println);
        }
    }

    //Need to fix such that the list of appointments doctors,pharmacists,patients access are all the same
    public Appointment findAppointmentById(String appointmentId) {
        return doctorRepository.findAppointmentById(appointmentId);
    }

    public void recordAppointmentOutcome(Doctor doctor, Appointment appointment, String outcome) {
        doctor.recordOutcome(appointment, outcome);
        appointment.setCompleted(true);
        logger.logInfo("Recorded outcome for appointment: " + appointment.getAppointmentID());
    }

    public void handleAppointmentOutcome(Doctor doctor, Scanner scanner) {
        System.out.print("Enter Appointment ID to record outcome: ");
        String appointmentId = scanner.nextLine();
        Appointment appointment = findAppointmentById(appointmentId); // Find the appointment by ID

        if (appointment != null) {
            System.out.print("Enter outcome: ");
            String outcome = scanner.nextLine();
            recordAppointmentOutcome(doctor, appointment, outcome);
            System.out.println("Appointment outcome recorded.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void createAppointment(String doctorId, Appointment appointment) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor != null) {
            if (!conflictChecker.checkForConflicts(appointment, Appointment.getAllAppointments())) {
                doctor.getSchedule().addAvailableSlot(new TimeSlot(
                        appointment.getAppointmentID(),
                        appointment.getAppointmentTime().toLocalDate(),  // Use toLocalDate() to get just the date
                        appointment.getAppointmentTime().toLocalTime().toString(),
                        appointment.getAppointmentTime().plusHours(1).toLocalTime().toString()
                ));
                System.out.println("Appointment scheduled: " + appointment);
            } else {
                System.out.println("Appointment conflict detected. Please choose another time");
            }
        }
    }

    public void updateAppointment(String doctorId, Appointment oldAppointment, Appointment newAppointment) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId); // Fetch the doctor using the doctorId
        if (doctor != null) {
            doctor.removeAppointment(oldAppointment); // Remove the old appointment
            doctor.addAppointment(newAppointment); // Add the new appointment
            logger.logInfo("Doctor " + doctor.getName() + " updated appointment from " + oldAppointment + " to " + newAppointment);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void cancelAppointment(String doctorId, Appointment appointment) {
        Doctor doctor = doctorRepository.findDoctorById(doctorId); // Fetch the doctor using the doctorId
        if (doctor != null) {
            doctor.removeAppointment(appointment); // Remove the appointment
            logger.logInfo("Doctor " + doctor.getName() + " canceled appointment: " + appointment);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public void viewAvailableSlots(String doctorId) {
        List<TimeSlot> availableSlots = doctorRepository.getAvailableSlots(doctorId);
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots.");
        } else {
            for (TimeSlot slot : availableSlots) {
                System.out.println(slot);
            }
        }
    }
    public List<Patient> getPatients(String doctorId) {
        return doctorRepository.getPatientsByDoctorId(doctorId);
    }
}
