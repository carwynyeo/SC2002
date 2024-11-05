package com.hospitalmanagementsystem.MedicalRecord;

import com.hospitalmanagementsystem.Utility.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String patientID;
    private String name;
    private String bloodType;
    private List<String> diagnoses;
    private List<String> treatments;
    private ContactInfo contactInfo;

    public MedicalRecord(String patientID, String name, String bloodType) {
        this.patientID = patientID;
        this.name = name;
        this.bloodType = bloodType;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.contactInfo = new ContactInfo();
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
