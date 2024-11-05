package com.hospitalmanagementsystem.Utility;

public class ContactInfo {
    private String phoneNumber;
    private String email;

    public ContactInfo(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void updateContactInfo(String newPhoneNumber, String newEmail) {
        this.phoneNumber = newPhoneNumber;
        this.email = newEmail;
        System.out.println("Contact information updated.");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDetails() {return email + phoneNumber;}
}
