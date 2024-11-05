package com.hospitalmanagementsystem.Model;

public abstract class Staff extends User {
    private String staffId;

    public Staff(String id, String name, String password, String role) {
        super(id, name, password, role);
        this.staffId = id;
    }

    public void addStaff(User newStaff) {
        System.out.println("Staff added: " + newStaff.name);
    }

    public void removeStaff(User staff) {
        System.out.println("Staff removed: " + staff.name);
    }

    public void viewStaffDetails() {
        System.out.println("Staff ID: " + staffId + ", Name: " + name + ", Role: " + role);
    }
}

