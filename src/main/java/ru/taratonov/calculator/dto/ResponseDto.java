package ru.taratonov.calculator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private String description;
    private double amountVacationMoney;
    private double personalIncomeTax;
    private double totalAmountVacationMoney;
}
