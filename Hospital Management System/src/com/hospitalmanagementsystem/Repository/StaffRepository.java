package com.hospitalmanagementsystem.Repository;


import com.hospitalmanagementsystem.Model.Staff;
import com.hospitalmanagementsystem.Service.UserFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//Fit all the Staff here
public class StaffRepository {
    private final String filePath = "staff.csv"; // Path to the Staff CSV file
    private final Map<String, Staff> staffMembers = new HashMap<>();

    public StaffRepository() {
        loadStaffFromFile();
    }

    // Load staff members from the CSV file
    private void loadStaffFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV is comma-separated
                if (data.length >= 4) { // Adjust for the fields in Staff CSV
                    String id = data[0];
                    String name = data[1];
                    String password = data[2];
                    String role = data[3];

                    // Create staff using UserFactory
                    Staff staff = (Staff) UserFactory.createUser(id, name, password, role, null);
                    if (staff != null) {
                        staffMembers.put(id, staff);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading staff from file: " + e.getMessage());
        }
    }

    // Save all staff members to the CSV file
    public void saveStaffToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Staff staff : staffMembers.values()) {
                bw.write(staff.getId() + "," + staff.getName() + "," + staff.getPassword() + "," + staff.getRole());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving staff to file: " + e.getMessage());
        }
    }

    // Add a new staff member and save to file
    public void addStaff(Staff staff) {
        if (staff != null && !staffMembers.containsKey(staff.getId())) {
            staffMembers.put(staff.getId(), staff);
            saveStaffToFile();
        } else {
            System.out.println("Staff already exists or invalid entry.");
        }
    }

    // Find a staff member by ID
    public Optional<Staff> findStaffById(String id) {
        return Optional.ofNullable(staffMembers.get(id));
    }

    // Update an existing staff member
    public void updateStaff(Staff staff) {
        if (staff != null && staffMembers.containsKey(staff.getId())) {
            staffMembers.put(staff.getId(), staff);
            saveStaffToFile();
        } else {
            System.out.println("Staff not found.");
        }
    }

    // Delete a staff member
    public void deleteStaff(String id) {
        if (staffMembers.containsKey(id)) {
            staffMembers.remove(id);
            saveStaffToFile();
            System.out.println("Staff deleted successfully.");
        } else {
            System.out.println("Staff not found.");
        }
    }

    // List all staff members (optional)
    public Map<String, Staff> getAllStaff() {
        return staffMembers;
    }
}
