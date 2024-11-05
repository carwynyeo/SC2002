package com.hospitalmanagementsystem.Billing;

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private double amount;
    private String status;

    public Payment(String paymentId, String paymentMethod, double amount) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = "Pending"; // Initial status
    }

    // Process the payment
    public void processPayment() {
        if (amount > 0) {
            status = "Completed";
            System.out.println("Payment of $" + amount + " processed successfully using " + paymentMethod);
        } else {
            System.out.println("Invalid payment amount.");
        }
    }

    // Refund the payment
    public void refundPayment() {
        if (status.equals("Completed")) {
            status = "Refunded";
            System.out.println("Payment of $" + amount + " refunded.");
        } else {
            System.out.println("Payment not eligible for refund.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void viewPaymentDetails() {
        System.out.println("Payment ID: " + paymentId + ", Amount: $" + amount + ", Status: " + status);
    }
}

