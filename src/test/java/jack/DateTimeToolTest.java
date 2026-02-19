package jack;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateTimeToolTest {

    @Test
    public void parseDateTimeWithDayMonthYear() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("30/1/2026 1800");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
        assertEquals(18, dt.getHour());
        assertEquals(0, dt.getMinute());
    }

    @Test
    public void parseDateTimeWithDayMonthYearAlternative() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("30/01/2026 1800");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
    }

    @Test
    public void parseDateTimeWithIsoFormat() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("2026-01-30 18:00:00");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
    }

    @Test
    public void parseDateTimeWithMonthDayYearFormat() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("01/30/2026");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
    }

    @Test
    public void parseDateTimeWithUnderscoreFormat() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("2026_01_30");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
    }

    @Test
    public void parseDateTimeWithCompactFormat() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("20260130");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
    }

    @Test
    public void parseDateTimeWithSlashFormat() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("2026/01/30");
        assertEquals(2026, dt.getYear());
        assertEquals(1, dt.getMonthValue());
        assertEquals(30, dt.getDayOfMonth());
    }

    @Test
    public void formatDateTimeWithHoursOnly() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("30/1/2026 1800");
        String formatted = DateTimeTool.formatDateTime(dt);
        assertEquals("Jan 30 2026 6PM", formatted);
    }

    @Test
    public void formatDateTimeWithHoursAndMinutes() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("30/1/2026 1801");
        String formatted = DateTimeTool.formatDateTime(dt);
        assertEquals("Jan 30 2026 6.01PM", formatted);
    }

    @Test
    public void formatDateTimeWithSeconds() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("30/1/2026 18:01:30");
        String formatted = DateTimeTool.formatDateTime(dt);
        assertEquals("Jan 30 2026 6.01.30PM", formatted);
    }

    @Test
    public void parseAndFormatRoundTrip() throws Excep {
        String original = "30/1/2026 1800";
        LocalDateTime dt = DateTimeTool.parseDateTime(original);
        String formatted = DateTimeTool.formatDateTime(dt);
        LocalDateTime dt2 = DateTimeTool.parseDateTime(formatted);
        assertEquals(dt, dt2);
    }

    @Test
    public void parseDateTimeDateOnlyDefaultsToMidnight() throws Excep {
        LocalDateTime dt = DateTimeTool.parseDateTime("30/1/2026");
        assertEquals(0, dt.getHour());
        assertEquals(0, dt.getMinute());
        assertEquals(0, dt.getSecond());
    }
}
