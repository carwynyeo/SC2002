package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Model.*;
import com.hospitalmanagementsystem.Service.UserFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private final String filePath = "users.csv";
    private final Map<String, User> users = new HashMap<>();

    public UserRepository() {
        loadUsersFromFile();
    }

    // Load users from the CSV file into the in-memory storage
    private void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String id = data[0];
                    String name = data[1];
                    String password = data[2];
                    String role = data[3];
                    // Use UserFactory to create the correct User subtype based on role
                    String bloodType;
                    User user = UserFactory.createUser(id, name, password, role, bloodType);
                    if (user != null) {
                        users.put(id, user);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }
    // Save all users to the CSV file
    private void saveUsersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users.values()) {
                bw.write(user.getId() + "," + user.getName() + "," + user.getPassword() + "," + user.getRole());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }
    // Find a user by their ID
    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }
    // Add a new user to the repository and save to the CSV file
    public void addUser(User user) {
        if (user != null && !users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            saveUsersToFile();
            System.out.println("User added successfully.");
        } else {
            System.out.println("User already exists or invalid user.");
        }
    }
    // Update a user's details in the repository and save to the CSV file
    public void updateUser(User user) {
        if (user != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            saveUsersToFile();
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
    // Delete a user from the repository and save to the CSV file
    public void deleteUser(String id) {
        if (users.containsKey(id)) {
            users.remove(id);
            saveUsersToFile();
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // List all users (optional, for administrative purposes)
    public Map<String, User> getAllUsers() {
        return users;
    }
}
