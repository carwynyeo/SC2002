package com.hospitalmanagementsystem.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Probably just to add DateTime, not sure of how to incorporate this yet
public class DateTime {
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}

