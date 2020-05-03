package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatterHelper {
    private final static String pattern = "dd-MM-yyyy - HH:mm:ss";

    public static String format(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(pattern));
    }
}
