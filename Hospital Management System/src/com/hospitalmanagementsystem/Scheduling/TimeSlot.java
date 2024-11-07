package com.hospitalmanagementsystem.Scheduling;

public class TimeSlot {
    private String slotId;
    private String date;
    private String startTime;
    private String endTime;
    private boolean isAvailable;

    public TimeSlot(String slotId, String date, String startTime, String endTime) {
        this.slotId = slotId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true; // Default to available
    }

    public String getDate() {
        return date;
    }
    public void markAsBooked() {
        isAvailable = false;
        System.out.println("Time slot " + slotId + " marked as booked.");
    }

    public void markAsAvailable() {
        isAvailable = true;
        System.out.println("Time slot " + slotId + " marked as available.");
    }
}

