package ru.taratonov.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.taratonov.calculator.dto.ErrorDto;
import ru.taratonov.calculator.exception.InvalidDateRangeException;
import ru.taratonov.calculator.exception.NumberOfVacationDaysException;
import ru.taratonov.calculator.exception.ValueLessThanZeroException;
import ru.taratonov.calculator.exception.WrongYearException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValueLessThanZeroException.class, NumberOfVacationDaysException.class,
            InvalidDateRangeException.class, WrongYearException.class, MethodArgumentTypeMismatchException.class})
    public ErrorDto handleBadRequestException(Exception ex) {
        return new ErrorDto(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
    }
}
