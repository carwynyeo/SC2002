package com.hospitalmanagementsystem.Model;

import com.hospitalmanagementsystem.Inventory.Inventory;
import com.hospitalmanagementsystem.Inventory.InventoryManager;
import com.hospitalmanagementsystem.Utility.Logger;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Staff{
    private final List<User> staffList; //Keyword final to prevent reassignment to another object
    private InventoryManager inventoryManager;
    private Logger logger;

    //Administrator constructor
    public Administrator(String id, String name, String password) {
        super(id, name, password, "Administrator");
        this.staffList = new ArrayList<>();
        this.inventoryManager = new InventoryManager();
        this.logger = new Logger();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }

    public List<User> getStaffList() {
        return staffList;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

