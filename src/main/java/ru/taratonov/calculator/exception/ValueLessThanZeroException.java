package ru.taratonov.calculator.exception;

public class ValueLessThanZeroException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Input value should be greater than 0";
    }
}
