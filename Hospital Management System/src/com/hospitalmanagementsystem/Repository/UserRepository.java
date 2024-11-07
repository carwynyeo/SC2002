package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    // In-memory storage for users
    private final Map<String, User> users = new HashMap<>();

    // Find a user by their ID
    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    // Add a new user to the repository
    public void addUser(User user) {
        if (user != null && !users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            System.out.println("User already exists or invalid user.");
        }
    }

    // Update a user's details in the repository
    public void updateUser(User user) {
        if (user != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            System.out.println("User not found.");
        }
    }

    // Delete a user from the repository
    public void deleteUser(String id) {
        if (users.containsKey(id)) {
            users.remove(id);
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
