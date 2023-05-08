package ru.taratonov.calculator.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Getter
@Component
public class HolidayManager {

    @Value("${listOfHolidays}")
    private List<LocalDate> holidays;

    public int countVacationDays(List<LocalDate> inputNumOfVacationDays) {
        return (int) inputNumOfVacationDays.stream().filter(day -> !holidays.contains(day)).count();
    }

}
