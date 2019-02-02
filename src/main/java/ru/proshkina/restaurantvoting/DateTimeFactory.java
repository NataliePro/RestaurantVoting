package ru.proshkina.restaurantvoting;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeFactory {

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public LocalTime getTimeLimit() {
        return LocalTime.of(11, 0);
    }
}
