package com.kw.evcs.common.util;

import java.time.LocalDateTime;
import java.util.Objects;

public class DateUtil {

    public static LocalDateTime parseDate(String originalDate) {
        if (Objects.isNull(originalDate) || originalDate.isBlank() || originalDate.length() != 14) {
            return LocalDateTime.now();
        }

        int year = Integer.parseInt(originalDate.substring(0, 4));
        int month = Integer.parseInt(originalDate.substring(4, 6));
        int day = Integer.parseInt(originalDate.substring(6, 8));
        int hour = Integer.parseInt(originalDate.substring(8, 10));
        int minute = Integer.parseInt(originalDate.substring(10, 12));
        int second = Integer.parseInt(originalDate.substring(12, 14));

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
