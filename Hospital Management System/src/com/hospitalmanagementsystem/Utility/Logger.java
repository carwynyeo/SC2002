package com.hospitalmanagementsystem.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final List<String> logs = new ArrayList<>();

    public void logInfo(String message) {
        String logMessage = "[INFO] " + getCurrentTimestamp() + ": " + message;
        logs.add(logMessage);
        System.out.println(logMessage);
    }

    public void logError(String message) {
        String logMessage = "[ERROR] " + getCurrentTimestamp() + ": " + message;
        logs.add(logMessage);
        System.err.println(logMessage);
    }

    public void viewLog() {
        System.out.println("Log History:");
        for (String log : logs) {
            System.out.println(log);
        }
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}
