package com.hospitalmanagementsystem.Service;

public class AuthenticationService {
    private String userId;
    private String password;

    public AuthenticationService(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean validateCredentials(String inputId, String inputPassword) {
        return userId.equals(inputId) && password.equals(inputPassword);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully.");
    }
}