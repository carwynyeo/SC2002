package com.hospitalmanagementsystem.Scheduling;

import java.util.Timer;
import java.util.TimerTask;


// Will be a bonus feature, that sends doctors/patients reminders of their scheduled appointment coming up
// Also schedules Random Inventory checks for Pharmacists
public class Scheduler {

    public void scheduleAppointmentReminders(long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Sending appointment reminders...");
            }
        }, delay);
    }

    public void scheduleInventoryCheck(long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Performing inventory check...");
            }
        }, delay);
    }
}


