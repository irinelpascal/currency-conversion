package com.example.currencyconversion.service;

import com.example.currencyconversion.dto.CurrencyConversionDTO;

import java.math.BigDecimal;

public interface CurrencyService {

    CurrencyConversionDTO getCurrencyConversion(String from, String to, BigDecimal quantity);
}
