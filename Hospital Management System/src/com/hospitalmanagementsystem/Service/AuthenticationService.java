package com.hospitalmanagementsystem.Service;

import java.util.regex.Pattern;

public class AuthenticationService {
    private final String userId;
    private String password;

    public AuthenticationService(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // Validate credentials during login
    public boolean validateCredentials(String inputId, String inputPassword) {
        return userId.equals(inputId) && password.equals(inputPassword);
    }

    // Change password with validation
    public boolean changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            System.out.println("Incorrect password.");
            return false;
        }

        if (!isValidPassword(newPassword)) {
            System.out.println(
                    "Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.");
            return false;
        }

        password = newPassword;
        System.out.println("Password changed successfully.");
        return true;
    }

    // Validate password strength
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[a-z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find();
    }
}