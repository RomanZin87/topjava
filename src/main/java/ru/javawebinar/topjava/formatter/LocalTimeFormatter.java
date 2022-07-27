package ru.javawebinar.topjava.formatter;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String text, Locale locale) {
        return DateTimeUtil.parseLocalTime(text);
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
