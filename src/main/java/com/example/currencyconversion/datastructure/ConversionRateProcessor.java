package com.example.currencyconversion.datastructure;

import com.example.currencyconversion.exception.CurrencyNotRegisteredException;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.example.currencyconversion.datastructure.CurrencyStructure.availableCurrencies;

@Slf4j
public class ConversionRateProcessor {

    public ConversionResult findConversionRate(String fromCurrency, String toCurrency, List<Node> currencies) {

        if (isCurrencyRegistered(fromCurrency, toCurrency)) {  // check if one or both currencies are not registered in our data structure
            ConversionResult directlyAvailableConversion = getCurrencyIfDirectlyAvailable(fromCurrency, toCurrency, currencies); // or directly inverted
            if (directlyAvailableConversion != null) {
                return directlyAvailableConversion;
            }
            // else we redirect processing
            Map<String, Double> maxRatioMap = new HashMap<>();
            Map<String, String> predecessorMap = new HashMap<>();

            maxRatioMap.put(fromCurrency, 1.0);
            predecessorMap.put(fromCurrency, null); // No predecessor for the start currency

            int n = currencies.size();

            // (Bellman-Ford algorithm)
            for (int i = 0; i < n - 1; i++) {
                boolean updated = false;
                for (Node node : currencies) {
                    if (maxRatioMap.containsKey(node.getFrom())) {
                        double currentRatio = maxRatioMap.get(node.getFrom()) * node.getConversionMultiply();
                        if (currentRatio > maxRatioMap.getOrDefault(node.getTo(), 0.0)) {
                            maxRatioMap.put(node.getTo(), currentRatio);
                            predecessorMap.put(node.getTo(), node.getFrom());
                            updated = true;
                        }
                    }
                }
                if (!updated) {
                    break;
                }
            }

            List<String> path = new LinkedList<>();
            String currentCurrency = toCurrency;

            while (currentCurrency != null) {
                path.add(0, currentCurrency);
                currentCurrency = predecessorMap.get(currentCurrency);
            }

            if (path.size() == 1 && !path.get(0).equals(fromCurrency)) {

                return new ConversionResult(-1.0, Collections.emptyList(), "Could not find a way to analyze the rate for "
                        + fromCurrency + " " + toCurrency);
            }

            return new ConversionResult(maxRatioMap.getOrDefault(toCurrency, -1.0), path, "Successfully processed best conversion for "
                    + fromCurrency + " and " + toCurrency);
        }
        log.error("{}/{}! One of the currencies or both are not registered on this application", fromCurrency, toCurrency);
        throw new CurrencyNotRegisteredException(fromCurrency + "/" + toCurrency + "! " + "One of the currencies or both are not registered on this application");
    }

    private ConversionResult getCurrencyIfDirectlyAvailable(String startCurrency, String endCurrency, List<Node> currencies) {

        AtomicBoolean invertedConversion = new AtomicBoolean(false);

        List<Node> currencyNode = currencies.stream()
                .filter(currency -> currency.getFrom().equals(startCurrency) && currency.getTo().equals(endCurrency))
                .collect(Collectors.collectingAndThen(Collectors.toList(), currency -> {
                    if (!currency.isEmpty()) {
                        invertedConversion.set(false);
                        return currency;
                    }
                    invertedConversion.set(true);
                    List<Node> collect = currencies.stream()
                            .filter(invertedCurrency -> invertedCurrency.getFrom().equals(endCurrency) && invertedCurrency.getTo().equals(startCurrency))
                            .collect(Collectors.toList());
                    return collect.isEmpty() ? null : collect;
                }));

        if (currencyNode != null && !invertedConversion.get()) {
            return ConversionResult.builder()
                    .conversionMultiple(currencyNode.get(0).getConversionMultiply())
                    .shortestPath(Collections.emptyList())
                    .description("Successfully retrieved conversion between " + startCurrency + "/" + endCurrency)
                    .build();
        } else if (currencyNode != null && invertedConversion.get()) {
            return ConversionResult.builder()
                    .conversionMultiple(1 / currencyNode.get(0).getConversionMultiply()) // -1
                    .shortestPath(Collections.emptyList())
                    .description("Successfully retrieved conversion between " + startCurrency + "/" + endCurrency)
                    .build();
        } else
            return null; // conversion is not directly available (neither inverted), so we redirect processing to Bellman-Ford algorithm
    }

    private boolean isCurrencyRegistered(String from, String to) {
        return availableCurrencies.contains(from) && availableCurrencies.contains(to);
    }

}