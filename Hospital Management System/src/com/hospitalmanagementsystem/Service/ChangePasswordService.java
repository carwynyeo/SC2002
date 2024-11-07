package com.hospitalmanagementsystem.Service;

import java.util.regex.Pattern;

public class ChangePasswordService {
    private String password;

    public ChangePasswordService(String password) {
        this.password = password;
    }

    // Change password with validation
    public boolean changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            System.out.println("Incorrect password.");
            return false;
        }

        if (!isValidPassword(newPassword)) {
            System.out.println("Password must be at least 8 characters long, contain an uppercase letter, a number, and a special character.");
            return false;
        }

        password = newPassword;
        System.out.println("Password changed successfully.");
        return true;
    }

    // Validate password strength
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }
        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            return false;
        }
        return true;
    }
}
