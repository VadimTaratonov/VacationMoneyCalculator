package ru.taratonov.calculator.exception;

public class InvalidDateRangeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Your finish date of vacation is before the start date ";
    }
}
