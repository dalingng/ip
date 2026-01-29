import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

public class DateTimeTool {

    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy").withLocale(Locale.ENGLISH);
    public static DateTimeFormatter getTimeFormat(){
        return timeFormat;
    }
    private static final String[] PATTERNS = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd",
            "yyyy_MM_dd HH:mm:ss", "yyyy_MM_dd", "yyyyMMddHHmmss", "yyyyMMdd",
            "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy",
            "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy",
            "d/M/yyyy HH:mm:ss", "d/M/yyyy",
            "MMM dd yyyy","MMM dd yyyy HH:mm:ss",
            "dd-MMM-yyyy HH:mm:ss", "dd-MMM-yyyy","MMM dd yyyy","MMM dd yyyy HH:mm:ss"
    };

    public static LocalDateTime parseDateTime(String timeStr) throws Excep {
        // is not null
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new Excep("time is null");
        }
        String trimStr = timeStr.trim();
        String[] tArgs=trimStr.split(" ");
        if(tArgs.length < 1){
            throw  new Excep("datetime format error");
        }else if(tArgs.length == 2){
            if(tArgs[1].matches("^\\d+$")){
                long minutes=Long.parseLong(tArgs[1]);
                LocalTime lt = LocalTime.MIN.plusMinutes(minutes);
                trimStr = tArgs[0]+" "+lt.format(DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(Locale.ENGLISH));
            }
        }
        String tStr = trimStr;
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
