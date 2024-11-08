package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Service.ChangePasswordService;

import java.util.Scanner;

public class UserController {
    protected ChangePasswordService changePasswordService;

    public UserController(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    public void logout(User currentUser) {
        // Assuming there is a session management mechanism
        if (currentUser != null) {
            System.out.println("User " + currentUser.getName() + " has been logged out.");
            // Clear currentUser reference in session management (not shown)
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    // Method for changing password with old password validation
    public void changePassword(User currentUser, Scanner scanner) {
        if (currentUser == null) {
            System.out.println("No user is logged in. Please log in to change the password.");
            return;
        }

        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        // Use only oldPassword and newPassword as per the method signature in
        // ChangePasswordService
        if (changePasswordService.changePassword(oldPassword, newPassword)) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please check the requirements.");
        }
    }
}
