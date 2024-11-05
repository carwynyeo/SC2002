package com.hospitalmanagementsystem.Billing;

public class Billing {
    private String billId;
    private String patientId;
    private double totalAmount;
    private String status;

    public Billing(String billId, String patientId, double totalAmount) {
        this.billId = billId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.status = "Unpaid"; // Default status
    }

    public void generateBill() {
        System.out.println("Bill ID: " + billId + ", Patient ID: " + patientId + ", Total Amount: $" + totalAmount + ", Status: " + status);
    }

    public void processPayment(double amount) {
        if (amount >= totalAmount) {
            status = "Paid";
            System.out.println("Payment processed. Bill status updated to: " + status);
        } else {
            System.out.println("Insufficient payment. Please pay the full amount.");
        }
    }

    public void viewBill() {
        System.out.println("Viewing bill: " + billId + " | Amount: $" + totalAmount + " | Status: " + status);
    }
}
