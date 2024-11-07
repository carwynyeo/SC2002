package com.hospitalmanagementsystem.Controller;

import com.hospitalmanagementsystem.Model.Administrator;
import com.hospitalmanagementsystem.Model.User;
import com.hospitalmanagementsystem.Repository.UserRepository;
import com.hospitalmanagementsystem.Service.AuthenticationService;

import java.util.Optional;
import java.util.Scanner;

public class UserController {
    protected AuthenticationService authenticationService;
    protected UserRepository userRepository;

    // Constructor that accepts AuthenticationService and UserRepository dependencies
    public UserController(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    // Modified login method to match loginAdministrator style
    public Optional<User> login(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String userPassword = scanner.nextLine();

        // Fetch the user from the repository
        User user = userRepository.findUserById(userId);

        if (user != null && user.getPassword().equals(userPassword)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public void logout(User currentUser) {
        // Clear the current User's session, if applicable
        System.out.println("User " + currentUser.getName() + " has been logged out.");
        currentUser = null; // Clear currentUser reference if necessary
    }

    // Method for changing password with old password validation
    public void changePassword(User currentUser, Scanner scanner) {
        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        if (authenticationService.changePassword(currentUser, oldPassword, newPassword)) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please check the requirements.");
        }
    }
}
