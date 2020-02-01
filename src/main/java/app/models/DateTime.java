package app.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import app.exceptions.WrongDateTimeFormatException;

public class DateTime {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    public static final String DEFAULT_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final String DEFAULT_OUTPUT_FORMAT = "MMM d yyyy hh:mma";
    public static final String DEFAULT_STORAGE_FORMAT = "yyyyMMddHHmmss";

    private DateTime(String dateTimeStr, String format) throws WrongDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            this.date = LocalDateTime.parse(dateTimeStr, formatter);
            this.formatter = DateTimeFormatter.ofPattern(DEFAULT_OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException("Accepted Date Time Format: " + format);
        }
    }

    private DateTime(LocalDateTime date, DateTimeFormatter formatter) {
        this.date = date;
        this.formatter = formatter;
    }

    public static DateTime from(String dateTimeStr) throws WrongDateTimeFormatException {
        return new DateTime(dateTimeStr, DEFAULT_INPUT_FORMAT);
    }

    public static DateTime fromFormat(String dateTimeStr, String format) throws WrongDateTimeFormatException {
        return new DateTime(dateTimeStr, format);
    }

    public static DateTime fromStorage(String dateTimeStr) throws WrongDateTimeFormatException {
        return new DateTime(dateTimeStr, DEFAULT_STORAGE_FORMAT);
    }

    public DateTime withFormat(String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return new DateTime(this.date, formatter);
    }

    public String toStorage() {
        return this.date.format(DateTimeFormatter.ofPattern(DEFAULT_STORAGE_FORMAT));
    }

    @Override
    public String toString() {
        return this.date.format(this.formatter);
    }
}