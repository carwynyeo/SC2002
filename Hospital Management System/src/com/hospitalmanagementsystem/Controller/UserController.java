package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Service.AuthenticationService;
import java.util.Scanner;

public class UserController {
    protected AuthenticationService authenticationService;

    // Constructor now accepts id and password
    public UserController(String id, String password) {
        this.authenticationService = new AuthenticationService(id, password);
    }

    // Remember to link this to all the controllers to validate id and password
    public void login(String inputId, String inputPassword) {
        if (authenticationService.validateCredentials(inputId, inputPassword)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid ID or password.");
        }
    }

    //Remember to link this to all the controllers so that the users can change their password
    // Change password method - now includes old password validation
    public void changePassword(Scanner scanner) {
        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        if (authenticationService.changePassword(oldPassword, newPassword)) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please check the requirements.");
        }
    }
}
