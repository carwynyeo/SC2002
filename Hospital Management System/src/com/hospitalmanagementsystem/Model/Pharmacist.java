package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.MedicalRecord.Prescription;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.List;

public class Pharmacist extends User{
    private InventoryManager inventoryManager;
    private Logger logger;

    public Pharmacist(String id, String name, String password, InventoryManager inventoryManager) {
        super(id, name, password, "Pharmacist");
        this.inventoryManager = inventoryManager;
        this.logger = new Logger();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public List<Prescription> getPrescriptions() { return prescriptions; }

    // Add prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }
}
