package app.util;

import org.junit.jupiter.api.Test;

import app.exceptions.WrongDateTimeFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTest {
    @Test
    public void createDate_correctDefaultFormat() throws WrongDateTimeFormatException {
        Date output = Date.from("2019-01-01 1800");
        assertEquals("Jan 1 2019 06:00PM", output.toString());
    }

    @Test
    public void createDate_wrongDefaultFormat_exceptionThrown() {
        try {
            Date output = Date.from("2019-01-01");
        } catch (WrongDateTimeFormatException e) {
            assertEquals("Accepted Date Time Format: yyyy-MM-dd HHmm", e.getMessage());
        }
    }

    @Test
    public void createDate_correctAlternateFormat() throws WrongDateTimeFormatException {
        Date output = Date.fromFormat("01-01-2019 0000", "dd-MM-yyyy HHmm");
        assertEquals("Jan 1 2019 12:00AM", output.toString());
    }

    @Test
    public void createDate_wrongAlternateFormat_exceptionThrown() {
        try {
            Date output = Date.fromFormat("2019-01-1", "dd-MM-yyyy HHmm");
        } catch (WrongDateTimeFormatException e) {
            assertEquals("Accepted Date Time Format: dd-MM-yyyy HHmm", e.getMessage());
        }
    }

    @Test
    public void outputDate_withFormat() throws WrongDateTimeFormatException {
        Date output = Date.from("2019-01-01 1800")
            .withFormat("dd-MM-yyyy");
        assertEquals("01-01-2019", output.toString());
    }
}