package com.hospitalmanagementsystem.Scheduling;

import java.util.Timer;
import java.util.TimerTask;

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


