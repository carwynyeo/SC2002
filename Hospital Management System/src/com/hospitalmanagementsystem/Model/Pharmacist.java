package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.MedicalRecord.Prescription;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.ArrayList;
import java.util.List;

public class Pharmacist extends Staff{
    private InventoryManager inventoryManager;
    private Logger logger;
    private List<Prescription> prescriptions;

    // Constructor
    public Pharmacist(String id, String name, String password, InventoryManager inventoryManager) {
        super(id, name, password, "Pharmacist");
        this.inventoryManager = inventoryManager;
        this.logger = new Logger();
        this.prescriptions = new ArrayList<>(); // Initialize prescriptions list
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    // Getters and setters
    public InventoryManager getInventoryManager() { return inventoryManager; }
    public void setInventoryManager(InventoryManager inventoryManager) { this.inventoryManager = inventoryManager; }

    public Logger getLogger() { return logger; }
    public void setLogger(Logger logger) { this.logger = logger; }

    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }

    // Method to add a prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }
}

