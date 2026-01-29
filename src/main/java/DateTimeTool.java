import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

public class DateTimeTool {

    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy h.mm.ssa").withLocale(Locale.ENGLISH);
    private static DateTimeFormatter timeFormatM = DateTimeFormatter.ofPattern("MMM dd yyyy h.mma").withLocale(Locale.ENGLISH);
    private static DateTimeFormatter timeFormatH = DateTimeFormatter.ofPattern("MMM dd yyyy ha").withLocale(Locale.ENGLISH);
    public static String formatDateTime(LocalDateTime time){

        if(time.getSecond()==0 && time.getMinute()==0){
            return time.format(timeFormatH);
        }
        if(time.getSecond()==0){
            return time.format(timeFormatM);
        }
        return time.format(timeFormat);
    }
    private static final String[] PATTERNS = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd",
            "yyyy_MM_dd HH:mm:ss", "yyyy_MM_dd", "yyyyMMddHHmmss", "yyyyMMdd",
            "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy",
            "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy",
            "d/M/yyyy HH:mm:ss", "d/M/yyyy",
            "d/M/yyyy hhmma", "d/M/yyyy HHmm",
            "MMM dd yyyy","MMM dd yyyy HH:mm:ss",
            "MMM dd yyyy HH:mm","MMM dd yyyy hh:mma","MMM dd yyyy hh:mm a",
            "MMM dd yyyy HH.mm","MMM dd yyyy hh.mma","MMM dd yyyy hh.mm a",
            "MMM dd yyyy H.m","MMM dd yyyy h.ma","MMM dd yyyy h.m a",
            "MMM dd yyyy H","MMM dd yyyy ha","MMM dd yyyy h a",
            "dd-MMM-yyyy HH:mm:ss", "dd-MMM-yyyy","MMM dd yyyy","MMM dd yyyy HH:mm:ss"
    };

    public static LocalDateTime parseDateTime(String timeStr) throws Excep {
        // is not null
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new Excep("time is null");
        }
        String tStr = timeStr.trim();
        // try all format
        return Arrays.stream(PATTERNS)
                .map(DateTimeFormatter::ofPattern)
                .map(formatter -> {
                    formatter=formatter.withLocale(Locale.ENGLISH);
                    try {
                        // parse LocalDateTime
                        return LocalDateTime.parse(tStr, formatter);
                    } catch (DateTimeParseException e1) {
                        try {
                            // parse LocalDate
                            return LocalDate.parse(tStr, formatter).atStartOfDay();
                        } catch (DateTimeParseException e2) {
                            // format error return null
                            return null;
                        }
                    }
                })
                .filter(result -> result != null)
                .findFirst()
                .orElseThrow(() -> new Excep("Not supported datetime format：" + tStr));
    }
}
