package ru.taratonov.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.taratonov.calculator.dto.ResponseDTO;
import ru.taratonov.calculator.service.CalculatorService;

import java.time.LocalDate;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(value = "/calculacte", params = {"numOfVacationDays", "averageYearSalary"})
    public ResponseDTO getVacationMoney(@RequestParam(value = "numOfVacationDays") int numOfVacationDays,
                                        @RequestParam(value = "averageYearSalary") double averageYearSalary) {
        return calculatorService.getVacationMoney(numOfVacationDays, averageYearSalary);
    }

    @GetMapping(value = "/calculacte", params = {"averageYearSalary", "startDayVacation", "endDayVacation"})
    public ResponseDTO getVacationMoney(@RequestParam(value = "averageYearSalary") double averageYearSalary,
                                        @RequestParam(value = "startDayVacation")
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDayVacation,
                                        @RequestParam(value = "endDayVacation")
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDayVacation) {
        return calculatorService.getVacationMoney(averageYearSalary, startDayVacation, endDayVacation);
    }
}
