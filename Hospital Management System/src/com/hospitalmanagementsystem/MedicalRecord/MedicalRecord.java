package com.hospitalmanagementsystem.MedicalRecord;

import com.hospitalmanagementsystem.Utility.ContactInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MedicalRecord {
    private final String patientID;
    private final String name;
    private final String bloodType;
    private final List<String> diagnoses;
    private final List<String> treatments;
    private final ContactInfo contactInfo;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public MedicalRecord(String patientID, String name, String bloodType) {
        this.patientID = patientID;
        this.name = name;
        this.bloodType = bloodType;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.contactInfo = new ContactInfo("default@example.com", "000-000-0000"); // default initialization
    }

    // Getter for record details
    public String getRecordDetails() {
        return "Medical Record [" +
                "ID: " + patientID +
                ", Name: " + name +
                ", Blood Type: " + bloodType +
                ", Diagnoses: " + diagnoses +
                ", Treatments: " + treatments +
                ", Contact Info: " + contactInfo.getDetails() +
                "]";
    }

    public void setContactInfo(String email, String phone) {
        if (isValidEmail(email)) {
            contactInfo.updateContactInfo(email, phone);
        } else {
            System.out.println("Invalid email format. Please provide a valid email address.");
        }
    }

    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    // validation for email and number
    private boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }
}
