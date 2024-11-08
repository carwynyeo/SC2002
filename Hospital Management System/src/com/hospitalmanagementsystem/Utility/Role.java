package com.hospitalmanagementsystem.Utility;


//The core functionality for this is for the StaffList.

//Meant to show demonstrate role specific permissions, probably want to add this at the start
//When a User creates account, then we show this to tell them their role specific permissions?


public class Role {
    private String roleId;
    private String roleName; // e.g., Patient, Doctor, Pharmacist, Administrator

    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    // Assign a new role to the user
    public void assignRole(String newRoleName) {
        this.roleName = newRoleName;
        System.out.println("Role updated to " + newRoleName);
    }

    // Return role-specific permissions
    public String getRolePermissions() {
        switch (roleName) {
            case "Patient":
                return "Access to personal medical records and appointment scheduling.";
            case "Doctor":
                return "Access to patient records and appointment management.";
            case "Pharmacist":
                return "Access to prescription information and inventory.";
            case "Administrator":
                return "Full access to all system functionalities.";
            default:
                return "No permissions available.";
        }
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
