package com.hospitalmanagementsystem.Inventory;

public class ReplenishmentRequest {
    private String requestId;
    private String medicineName;
    private String requestedBy;
    private int quantity;
    private String status;

    public ReplenishmentRequest(String requestId, String medicineName, String requestedBy, int quantity) {
        this.requestId = requestId;
        this.medicineName = medicineName;
        this.requestedBy = requestedBy;
        this.quantity = quantity;
        this.status = "Pending"; // Default status
    }

    public void submitRequest() {
        System.out.println("Replenishment request submitted for " + quantity + " units of " + medicineName);
    }

    public void approveRequest(String medication) {
        int currentStock = Inventory.getMedicationQuantity(medication);
        int newStock = currentStock + 50; // Example replenishment quantity
        Inventory.updateStock(medication, newStock);
        System.out.println("Replenishment approved for " + medication);
        System.out.println("Stock replenished: " + medication + " now has " + newStock + " units.");
    }

    public void updateRequestStatus(String newStatus) {
        status = newStatus;
        System.out.println("Replenishment request status updated to " + newStatus);
    }
}
