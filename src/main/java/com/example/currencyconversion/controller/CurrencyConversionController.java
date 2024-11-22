package com.example.currencyconversion.controller;

import com.example.currencyconversion.dto.CurrencyConversionDTO;
import com.example.currencyconversion.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {
    private final CurrencyService currencyService;

    @GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyConversionDTO calculateCurrencyConversionBeta(@PathVariable(name = "from") String from,
                                                                 @PathVariable(name = "to") String to,
                                                                 @PathVariable(name = "quantity") BigDecimal quantity) {
        return currencyService.getCurrencyConversion(from, to, quantity);
    }
}
