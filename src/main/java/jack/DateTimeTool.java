package jack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

/**
 * A utility class for handling date and time operations in the Jack application.
 * Provides methods for parsing and formatting date and time values.
 */
public class DateTimeTool {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private DateTimeTool() {
    }

    /**
     * Formats a LocalDateTime object into a human-readable string.
     * Uses different formats based on the precision of the time (seconds, minutes, or hours).
     * @param time The LocalDateTime object to format.
     * @return A formatted string representation of the date and time.
     */
    public static String formatDateTime(LocalDateTime time) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(
                "MMM dd yyyy h.mm.ssa").withLocale(Locale.ENGLISH);
        DateTimeFormatter timeFormatM = DateTimeFormatter.ofPattern(
                "MMM dd yyyy h.mma").withLocale(Locale.ENGLISH);
        DateTimeFormatter timeFormatH = DateTimeFormatter.ofPattern(
                "MMM dd yyyy ha").withLocale(Locale.ENGLISH);
        if (time.getSecond() == 0 && time.getMinute() == 0) {
            return time.format(timeFormatH);
        }
        if (time.getSecond() == 0) {
            return time.format(timeFormatM);
        }
        return time.format(timeFormat);
    }

    /**
     * Parses a string into a LocalDateTime object.
     * Tries multiple date and time formats to handle different input styles.
     * @param timeStr The string to parse as a date and time.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws Excep If the string is null, empty, or cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String timeStr) throws Excep {
        String[] patterns = new String[] {
            "yyyy-MM-dd HH", "yyyy-MM-dd HH:ss", "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss",
            "yyyy_MM_dd HH:mm:ss", "yyyy_MM_dd", "yyyyMMddHHmmss", "yyyyMMdd",
            "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy",
            "d/M/yyyy HH:mm:ss", "d/M/yyyy",
            "d/M/yyyy hhmma", "d/M/yyyy HHmm",
            "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy",
            "MMM dd yyyy", "MMM dd yyyy HH:mm:ss",
            "MMM dd yyyy HH:mm", "MMM dd yyyy hh:mma", "MMM dd yyyy hh:mm a",
            "MMM dd yyyy HH.mm", "MMM dd yyyy hh.mma", "MMM dd yyyy hh.mm a",
            "MMM dd yyyy H.m", "MMM dd yyyy h.ma", "MMM dd yyyy h.m a",
            "MMM dd yyyy H", "MMM dd yyyy ha", "MMM dd yyyy h a",
            "dd-MMM-yyyy HH:mm:ss", "dd-MMM-yyyy", "MMM dd yyyy", "MMM dd yyyy HH:mm:ss",
            "MMM dd yyyy h.mm.ssa"
        };
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new Excep("time is null");
        }
        String tStr = timeStr.trim();
        return Arrays.stream(patterns)
                .map(DateTimeFormatter::ofPattern)
                .map(formatter -> tryParseWithFormatter(formatter, tStr))
                .filter(result -> result != null)
                .findFirst()
                .orElseThrow(() -> new Excep("datetime format error: " + tStr));
    }

    /**
     * Tries to parse a time string with a specific formatter.
     * @param formatter The DateTimeFormatter to use for parsing.
     * @param timeStr The time string to parse.
     * @return A LocalDateTime if parsing succeeds, null otherwise.
     */
    private static LocalDateTime tryParseWithFormatter(
            DateTimeFormatter formatter, String timeStr) {
        formatter = formatter.withLocale(Locale.ENGLISH);
        try {
            return LocalDateTime.parse(timeStr, formatter);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(timeStr, formatter).atStartOfDay();
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }
}
