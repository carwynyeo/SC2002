package com.hospitalmanagementsystem.Model;

public abstract class Staff extends User {
    private String role;

    public Staff(String id, String name, String password, String role) {
        super(id, name, password);
        this.role = role;
    }

    public String getRole() { return role; }
}

