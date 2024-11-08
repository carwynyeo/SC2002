package com.hospitalmanagementsystem.Service;

import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.MedicalRecord.MedicalRecord;
import com.hospitalmanagementsystem.Model.*;

public class UserFactory {

    public static User createUser(String id, String name, String password, String role,String bloodType) {
        return switch (role.toLowerCase()) {
            case "doctor"-> new Doctor(id,name,password);
            case "pharmacist"-> new Pharmacist(id,name,password,new InventoryManager());
            case "administrator" -> new Administrator(id, name, password, role);
            case "patient" -> new Patient(id, name, password, bloodType,new MedicalRecord(id,name,bloodType));
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }
}
