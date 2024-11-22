package com.example.currencyconversion.service.impl;

import com.example.currencyconversion.datastructure.ConversionRateProcessor;
import com.example.currencyconversion.datastructure.ConversionResult;
import com.example.currencyconversion.datastructure.CurrencyStructure;
import com.example.currencyconversion.datastructure.Node;
import com.example.currencyconversion.dto.CurrencyConversionDTO;
import com.example.currencyconversion.exception.ProcessRateException;
import com.example.currencyconversion.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public CurrencyConversionDTO getCurrencyConversion(String from, String to, BigDecimal quantity) {

        List<Node> currencies = CurrencyStructure.getExistentCurrencies();

        ConversionResult conversionRate = new ConversionRateProcessor().findConversionRate(from, to, currencies);
        if (conversionRate.getConversionMultiple() == -1) {
            log.error("Could not process ratio between {} and {} currencies", from, to);
            throw new ProcessRateException("Could not process rate between " + from + " and " + to + " currencies");
        }
        return CurrencyConversionDTO
                .builder()
                .from(from)
                .to(to)
                .conversionMultiple(BigDecimal.valueOf(conversionRate.getConversionMultiple()))
                .quantity(quantity)
                .totalCalculatedAmount(quantity.multiply(BigDecimal.valueOf(conversionRate.getConversionMultiple())))
                .description(conversionRate.getDescription())
                .build();
    }
}
