package com.gad.spring_data_jpa.course.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MethodUtils {
    private MethodUtils() {
    }

    public static String dateTimeNowFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
