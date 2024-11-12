package com.hospitalmanagementsystem.MedicalRecord;

import com.hospitalmanagementsystem.Utility.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private final String patientID;
    private final String name;
    private final String bloodType;
    private final List<String> diagnoses;
    private final List<String> treatments;
    private final ContactInfo contactInfo;

    public MedicalRecord(String patientID, String name, String bloodType, String email, String phone) {
        this.patientID = patientID;
        this.name = name;
        this.bloodType = bloodType;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.contactInfo = new ContactInfo(email, phone);
        // this.contactInfo = new ContactInfo(getContactInfo().getEmail(),
        // getContactInfo().getPhoneNumber());
    }

    // Getter for patient ID
    public String getPatientID() {
        return patientID;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for blood type
    public String getBloodType() {
        return bloodType;
    }

    // Getter for record details
    public String getRecordDetails() {
        return "Medical Record [ID: " + patientID + ", Name: " + name + ", Blood Type: " + bloodType +
                ", Diagnoses: " + diagnoses + ", Treatments: " + treatments +
                ", Contact Info: " + contactInfo.getDetails() + "]";
    }

    public void setContactInfo(String email, String phone) {
        contactInfo.updateContactInfo(email, phone);
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
}
