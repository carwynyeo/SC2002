package com.hospitalmanagementsystem.Boundary;

import java.util.Scanner;

public abstract class UserBoundary {
    protected String id;
    protected String name;
    protected String password;
    protected String role;

    public UserBoundary(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Abstract method to be implemented by subclasses
    public abstract void showMenu(Scanner scanner);

    // Common login method
    public void login(String password) {
        if (this.password.equals(password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid password.");
        }
    }

    // Common password change method
    public void changePassword(Scanner scanner) {
        System.out.print("Enter new password: ");
        this.password = scanner.nextLine();
        System.out.println("Password changed successfully.");
    }
}
