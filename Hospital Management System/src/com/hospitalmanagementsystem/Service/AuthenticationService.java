package com.hospitalmanagementsystem.Service;

import java.util.regex.Pattern;

public class AuthenticationService {
    protected final String userId;
    protected String password;

    public AuthenticationService(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // Validate credentials during login
    public boolean validateCredentials(String inputId, String inputPassword) {
        return userId.equals(inputId) && password.equals(inputPassword);
    }

    // Validate password strength
    protected boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[a-z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find();
    }
}
