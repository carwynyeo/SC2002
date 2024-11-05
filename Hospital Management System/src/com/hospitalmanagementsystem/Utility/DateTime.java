package com.hospitalmanagementsystem.Utility;

public class DateTime {
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}

