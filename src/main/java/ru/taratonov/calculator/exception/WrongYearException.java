package ru.taratonov.calculator.exception;

public class WrongYearException extends RuntimeException {
    @Override
    public String getMessage() {
        return "This application works only for the year 2023";
    }
}
