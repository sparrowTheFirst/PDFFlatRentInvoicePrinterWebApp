package org.example.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SystemDateTime {

    public static LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }

    public static LocalDate getDateNow() {
        return LocalDate.now();
    }
}