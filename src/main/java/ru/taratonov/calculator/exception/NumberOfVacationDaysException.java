package ru.taratonov.calculator.exception;

public class NumberOfVacationDaysException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Number of vacation days should be less than 28";
    }
}
