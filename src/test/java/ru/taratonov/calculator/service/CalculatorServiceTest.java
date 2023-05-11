package ru.taratonov.calculator.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.taratonov.calculator.exception.InvalidDateRangeException;
import ru.taratonov.calculator.exception.NumberOfVacationDaysException;
import ru.taratonov.calculator.exception.ValueLessThanZeroException;
import ru.taratonov.calculator.exception.WrongYearException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {
    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    void getVacationMoney() {
        ValueLessThanZeroException valueLessThanZeroException =
                assertThrows(ValueLessThanZeroException.class,
                        () -> {
                            int numOfVacationDays = -1;
                            double averageYearSalary = 60;
                            calculatorService.getVacationMoney(numOfVacationDays, averageYearSalary);
                        });
        assertEquals("Input value should be greater than 0",
                valueLessThanZeroException.getMessage());

        valueLessThanZeroException = assertThrows(ValueLessThanZeroException.class,
                () -> {
                    int numOfVacationDays = 7;
                    double averageYearSalary = -1.0;
                    calculatorService.getVacationMoney(numOfVacationDays, averageYearSalary);
                });
        assertEquals("Input value should be greater than 0",
                valueLessThanZeroException.getMessage());

        NumberOfVacationDaysException numberOfVacationDaysException =
                assertThrows(NumberOfVacationDaysException.class,
                        () -> {
                            int numOfVacationDays = 29;
                            double averageYearSalary = 60000.0;
                            calculatorService.getVacationMoney(numOfVacationDays, averageYearSalary);
                        });
        assertEquals("Number of vacation days should be less than 28",
                numberOfVacationDaysException.getMessage());

        int numOfVacationDays = 7;
        double averageYearSalary = 60000.0;
        assertEquals("Your average month salary less than minimum wage. " +
                        "The minimum wage will be used to calculate vacation money!",
                calculatorService.getVacationMoney(numOfVacationDays, averageYearSalary).getDescription());

        averageYearSalary = 360000.0;
        assertEquals("Vacation money was successfully calculated!",
                calculatorService.getVacationMoney(numOfVacationDays, averageYearSalary).getDescription());

        numOfVacationDays = 1;
        averageYearSalary = 351600;
        double vacationMoney = 1000.0;
        double personalIncomeTax = 130;
        double totalAmountVacationMoney = 870.0;
        assertEquals(vacationMoney,
                calculatorService.
                        getVacationMoney(numOfVacationDays, averageYearSalary).
                        getAmountVacationMoney());

        assertEquals(personalIncomeTax,
                calculatorService.
                        getVacationMoney(numOfVacationDays, averageYearSalary).
                        getPersonalIncomeTax());

        assertEquals(totalAmountVacationMoney,
                calculatorService.
                        getVacationMoney(numOfVacationDays, averageYearSalary).
                        getTotalAmountVacationMoney());
    }

    @Test
    void getVacationMoneyPeriodOfTime() {
        WrongYearException wrongYearException = assertThrows(WrongYearException.class,
                () -> {
                    LocalDate startDayVacation = LocalDate.of(2024, 5, 15);
                    LocalDate endDayVacation = LocalDate.of(2023, 5, 15);
                    double averageYearSalary = 10;
                    calculatorService.
                            getVacationMoneyPeriodOfTime(averageYearSalary, startDayVacation, endDayVacation);
                });
        assertEquals("This application works only for the year 2023",
                wrongYearException.getMessage());

        wrongYearException = assertThrows(WrongYearException.class,
                () -> {
                    LocalDate endDayVacation = LocalDate.of(2024, 5, 15);
                    LocalDate startDayVacation = LocalDate.of(2023, 5, 15);
                    double averageYearSalary = 10;
                    calculatorService.
                            getVacationMoneyPeriodOfTime(averageYearSalary, startDayVacation, endDayVacation);
                });
        assertEquals("This application works only for the year 2023",
                wrongYearException.getMessage());

        InvalidDateRangeException invalidDateRangeException = assertThrows(InvalidDateRangeException.class,
                () -> {
                    LocalDate startDayVacation = LocalDate.of(2023, 5, 15);
                    LocalDate endDayVacation = LocalDate.of(2023, 3, 15);
                    double averageYearSalary = 10;
                    calculatorService.
                            getVacationMoneyPeriodOfTime(averageYearSalary, startDayVacation, endDayVacation);
                });
        assertEquals("Your finish date of vacation is before the start date!",
                invalidDateRangeException.getMessage());
    }
}