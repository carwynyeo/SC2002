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

}

