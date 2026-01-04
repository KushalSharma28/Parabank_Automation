package com.parabank.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time operations
 */
public class DateTimeUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Get current date and time
     *
     * @return Current date and time as string
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Get current timestamp in milliseconds
     *
     * @return Current timestamp
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * Format time in milliseconds
     *
     * @param milliseconds Milliseconds to format
     * @return Formatted time string (HH:mm:ss.SSS)
     */
    public static String formatTime(long milliseconds) {
        long hours = milliseconds / 3600000;
        long minutes = (milliseconds % 3600000) / 60000;
        long seconds = (milliseconds % 60000) / 1000;
        long millis = milliseconds % 1000;
        
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }
}
