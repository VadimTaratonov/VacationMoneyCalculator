package ru.taratonov.calculator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.taratonov.calculator.dto.ResponseDto;
import ru.taratonov.calculator.exception.InvalidDateRangeException;
import ru.taratonov.calculator.exception.NumberOfVacationDaysException;
import ru.taratonov.calculator.exception.ValueLessThanZeroException;
import ru.taratonov.calculator.exception.WrongYearException;
import ru.taratonov.calculator.util.HolidayManager;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class CalculatorService {

    private final HolidayManager holidayManager;
    private final double MINIMUM_WAGE = 16_242.0;

    @Autowired
    public CalculatorService(HolidayManager holidayManager) {
        this.holidayManager = holidayManager;
    }

    public ResponseDto getVacationMoney(int numOfVacationDays, double averageYearSalary) {
        if (numOfVacationDays <= 0 || averageYearSalary <= 0)
            throw new ValueLessThanZeroException();
        else if (numOfVacationDays > 28) {
            throw new NumberOfVacationDaysException();
        } else {
            double averageMonthSalary = averageYearSalary / 12;
            double scale = Math.pow(10, 2);
            String description;
            if (Double.compare(averageMonthSalary, MINIMUM_WAGE) <= 0) {
                averageMonthSalary = MINIMUM_WAGE;
                description = "Your average month salary less than minimum wage. " +
                        "The minimum wage will be used to calculate vacation money!";
            } else {
                description = "Vacation money was successfully calculated!";
            }
            double vacationMoney = averageMonthSalary / 29.3 * numOfVacationDays;
            double personalIncomeTax = Math.round(vacationMoney * 0.13);
            return new ResponseDto(
                    description,
                    Math.round(vacationMoney * scale) / scale,
                    personalIncomeTax,
                    Math.round((vacationMoney - personalIncomeTax) * scale) / scale);
        }
    }

    public ResponseDto getVacationMoneyPeriodOfTime(double averageYearSalary, LocalDate startDayVacation, LocalDate endDayVacation) {
        if (endDayVacation.getYear() != 2023 || startDayVacation.getYear() != 2023) {
            throw new WrongYearException();
        } else if (endDayVacation.isBefore(startDayVacation)) {
            throw new InvalidDateRangeException();
        } else {
            int vacationDays = holidayManager.countVacationDays(
                    startDayVacation.datesUntil(endDayVacation.plusDays(1)).collect(Collectors.toList()));
            return getVacationMoney(vacationDays, averageYearSalary);
        }
    }
}
