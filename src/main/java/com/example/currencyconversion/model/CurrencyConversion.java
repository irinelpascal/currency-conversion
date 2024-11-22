package com.example.currencyconversion.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CurrencyConversion {

    private String from;
    private String to;
    private Double conversionMultiple;

    public CurrencyConversion(String from, String to, Double conversionMultiple) {
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }
}
