package com.hospitalmanagementsystem.Service;
import com.hospitalmanagementsystem.Model.Appointment;

//Used to check and ensure that user input is valid
public class ValidationService {

    // Validate appointment details
    public boolean validateAppointment(Appointment appointment) {
        if (appointment.getDoctor() == null || appointment.getPatient() == null || appointment.getDate() == null) {
            return false; // Invalid appointment
        }
        return true; // Valid appointment
    }

    // Validate user input for creating a new user
    public boolean validateUserInput(String id, String name) {
        if (id == null || id.isEmpty()) {
            System.out.println("ID cannot be empty");
            return false;
        }
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return false;
        }
        return true; // Valid input
    }

    // Validate email and phone format
    public boolean validateContactInfo(String email, String phone) {
        if (!email.contains("@") || phone.length() != 10) {
            System.out.println("Invalid email or phone number.");
            return false;
        }
        return true; // Valid contact info
    }

    // Validate medication name
    public boolean validateMedicationName(String medicationName) {
        if (medicationName == null || medicationName.trim().isEmpty()) {
            System.out.println("Medication name cannot be empty.");
            return false;
        }
        return true; // Valid medication name
    }
}

