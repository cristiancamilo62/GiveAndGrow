package com.giveandgrow.shared.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateHelper {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.US);

    private DateHelper() {
    	super();
    }
    
    
    public static boolean isValidDateTime(String dateTimeStr) {
        try {
            String normalizedDateTimeStr = normalizeDateTimeString(dateTimeStr);
            LocalDateTime.parse(normalizedDateTimeStr, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        String normalizedDateTimeStr = normalizeDateTimeString(dateTimeStr);
        return LocalDateTime.parse(normalizedDateTimeStr, DATE_TIME_FORMATTER);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    private static String normalizeDateTimeString(String dateTimeStr) {
        return dateTimeStr.replace(" a. m.", " AM").replace(" p. m.", " PM");
    }

}


