package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Administrator;
import com.hospitalmanagementsystem.Model.Appointment;
import com.hospitalmanagementsystem.Model.User;
import java.util.ArrayList;
import java.util.List;

public class AdministratorRepository {
    private final List<Administrator> administrators = new ArrayList<>();
    private final List<User> staffList = new ArrayList<>(); // To manage staff
    private final List<Appointment> appointments = new ArrayList<>(); // To manage appointments


    public void addAdministrator(Administrator admin) {
        administrators.add(admin);
    }

    public Administrator findAdministratorById(String id) {
        for (Administrator admin : administrators) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }

    public List<Administrator> getAllAdministrators() {
        return administrators;
    }

    public void addStaff(User staff) {
        staffList.add(staff);
    }

    public boolean removeStaffById(String id) {
        return staffList.removeIf(staff -> staff.getId().equals(id));
    }

    //This show be able to collect all Doctors,Pharmacists and Administrators
    //So this function will call getalladministrators, getalldoctors, get all Pharmacists
    //Make a Staff Repository to access all this (add,get,delete update Staff)
    public List<User> getAllStaff() {
        return staffList;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
