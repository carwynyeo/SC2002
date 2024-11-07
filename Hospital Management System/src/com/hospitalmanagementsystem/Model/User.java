package com.hospitalmanagementsystem.Model;

public abstract class User {
    protected String id;
    protected String name;
    protected String password;
    protected String role;

    public User(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {}

    public String getName() {
        return name;
    }
    public void setName(String name) {}

    public String getRole() {
        return role;
    }
    public void setRole(String role) {}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
