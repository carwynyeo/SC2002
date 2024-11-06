package com.hospitalmanagementsystem.Model;


import com.hospitalmanagementsystem.Service.AuthenticationService;

import java.util.Scanner;

public abstract class User {
    protected String id;
    protected String name;
    protected String password;
    protected String role;
    protected AuthenticationService authenticationService;

    public User(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.authenticationService = new AuthenticationService(id, password);
    }

    public void login(String password) {
        if (authenticationService.validateCredentials(password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid password.");
        }
    }

    public void changePassword(Scanner scanner) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        authenticationService.changePassword(newPassword);
        System.out.println("Password changed successfully.");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
