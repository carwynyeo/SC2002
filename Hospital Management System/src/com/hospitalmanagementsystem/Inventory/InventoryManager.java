package com.hospitalmanagementsystem.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Integer> inventoryList;

    public InventoryManager() {
        this.inventoryList = new HashMap<>();
    }

    // Add a new medication to the inventory
    public void addMedicine(String medicine, int quantity) {
        inventoryList.put(medicine, quantity);
        System.out.println(medicine + " added to inventory with quantity: " + quantity);
    }

    // Remove a medication from the inventory
    public void removeMedicine(String medicine) {
        if (inventoryList.containsKey(medicine)) {
            inventoryList.remove(medicine);
            System.out.println(medicine + " removed from inventory.");
        } else {
            System.out.println("Medicine not found in inventory.");
        }
    }

    // Update the stock of a medication
    public void updateStock(String medicine, int quantity) {
        if (inventoryList.containsKey(medicine)) {
            inventoryList.put(medicine, quantity);
            System.out.println("Stock updated for " + medicine + ": " + quantity + " units.");
        } else {
            System.out.println("Medicine not found in inventory.");
        }
    }

    // Track medications that are low in stock
    public void trackLowStock() {
        System.out.println("Low stock medications:");
        for (Map.Entry<String, Integer> entry : inventoryList.entrySet()) {
            if (entry.getValue() < 10) { // Threshold for low stock
                System.out.println(entry.getKey() + ": " + entry.getValue() + " units left.");
            }
        }
    }

    public Map<String, Integer> getInventoryList() {
        return inventoryList;
    }
}

