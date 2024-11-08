package com.hospitalmanagementsystem.Service;

public class ChangePasswordService extends AuthenticationService {

    public ChangePasswordService(String userId, String password) {
        super(userId, password);
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
}
