package com.hospitalmanagementsystem.Scheduling;

import java.util.ArrayList;
import java.util.List;


//Meant to manage doctors personal schedule
//Have yet to incorporate it
public class Schedule {
    private String doctorId;
    private List<TimeSlot> availableSlots;

    public Schedule(String doctorId) {
        this.doctorId = doctorId;
        this.availableSlots = new ArrayList<>();
    }

    public void addAvailableSlot(TimeSlot slot) {
        availableSlots.add(slot);
        System.out.println("Available slot added: " + slot);
    }

    public void removeSlot(TimeSlot slot) {
        availableSlots.remove(slot);
        System.out.println("Available slot removed: " + slot);
    }

    public List<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }
}
