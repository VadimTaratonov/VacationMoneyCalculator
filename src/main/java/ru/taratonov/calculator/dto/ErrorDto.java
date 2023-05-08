package ru.taratonov.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private String msg;
    private LocalDateTime errorTime;
    private HttpStatus httpStatus;
}
