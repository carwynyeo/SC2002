package com.hospitalmanagementsystem.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Integer> medicationList;

    public Inventory() {
        medicationList = new HashMap<>();
        medicationList.put("Paracetamol", 100);
        medicationList.put("Ibuprofen", 150);
    }

    public Map<String, Integer> getMedicationList() {
        return medicationList;
    }

    public void addMedication(String medication, int quantity) {
        medicationList.put(medication, quantity);
        System.out.println("Added " + quantity + " units of " + medication);
    }

    public void updateStock(String medication, int quantity) {
        medicationList.put(medication, quantity);
        System.out.println("Stock updated for " + medication + ": " + quantity + " units available.");
    }

    public Map<String, Integer> getInventoryList() {
        return medicationList;
    }

    public int getMedicationQuantity(String medication) {
        return medicationList.getOrDefault(medication, 0);
    }

    @Override
    public String toString() {
        return "Inventory: " + medicationList;
    }
}
