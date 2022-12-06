package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDate DATE_MIN = LocalDate.of(1,1,1);
    private static final LocalDate DATE_MAX = LocalDate.of(1,1,1);
    private static final LocalTime TIME_MIN = LocalTime.of(0,0);
    private static final LocalTime TIME_MAX = LocalTime.of(23,59);

    public static LocalDate atStartOfDayOrMin(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay().toLocalDate() : DATE_MIN;
    }

    public static LocalDate atStartOfNextDayOrMax(LocalDate localDate) {
        return localDate != null ? localDate.plus(1, ChronoUnit.DAYS).atStartOfDay().toLocalDate() : DATE_MAX;
    }

    public static LocalTime atStartOfTimeOrMin(LocalTime localTime) {
        return localTime != null ? localTime : TIME_MIN;
    }

    public static LocalTime atEndOfTimeOrMax(LocalTime localTime) {
        return localTime != null ? localTime : TIME_MAX;
    }
    public static boolean isBetweenHalfOpenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
    public static boolean isBetweenHalfOpenDate(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) < 0;
    }


    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static @Nullable LocalDate parseToLocalDate(@Nullable String str) {
        return StringUtils.hasLength(str)? LocalDate.parse(str) : null;
    }
    public static @Nullable LocalTime parseToLocalTime(@Nullable String str) {
        return StringUtils.hasLength(str)? LocalTime.parse(str) : null;
    }

}

