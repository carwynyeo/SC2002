package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Staff{
    private final List<User> staffList; //Keyword final to prevent reassignment to another object
    private final InventoryManager inventoryManager;
    private final Logger logger;

    //Administrator constructor
    public Administrator(String id, String name, String password) {
        super(id, name, password, "Administrator");
        this.staffList = new ArrayList<>();
        this.inventoryManager = new InventoryManager();
        this.logger = new Logger();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }

    public List<User> getStaffList() {
        return staffList;
    }
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    public Logger getLogger() {
        return logger;
    }
}

