package com.example.currencyconversion.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final public class CurrencyStructure {

    public static List<String> availableCurrencies = Arrays.asList("AUD", "USD", "CAD", "CHF", "DKK", "EUR", "GBP");

    public static List<Node> getExistentCurrencies() {
        List<Node> currencies = new ArrayList<>();
        currencies.add(new Node("AUD", "USD", 0.7754));
        currencies.add(new Node("USD", "CAD", 1.2330));
        currencies.add(new Node("CAD", "CHF", 0.9313));
        currencies.add(new Node("USD", "DKK", 6.6336));
        currencies.add(new Node("EUR", "USD", 1.1231));
        currencies.add(new Node("GBP", "CHF", 1.5620));
        return currencies;
    }

}
