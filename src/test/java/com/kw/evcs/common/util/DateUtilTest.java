package com.kw.evcs.common.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    @Test
    void parseTest() {
        // given
        String originalDate = "20210506200605";
        int year = Integer.parseInt(originalDate.substring(0, 4));
        int month = Integer.parseInt(originalDate.substring(4, 6));
        int day = Integer.parseInt(originalDate.substring(6, 8));
        int hour = Integer.parseInt(originalDate.substring(8, 10));
        int minute = Integer.parseInt(originalDate.substring(10, 12));
        int second = Integer.parseInt(originalDate.substring(12, 14));

        // when
        LocalDateTime dateTime = DateUtil.parseDate(originalDate);

        // then
        assertEquals(dateTime.getYear(), year);
        assertEquals(dateTime.getMonthValue(), month);
        assertEquals(dateTime.getDayOfMonth(), day);
        assertEquals(dateTime.getHour(), hour);
        assertEquals(dateTime.getMinute(), minute);
        assertEquals(dateTime.getSecond(), second);
    }
}