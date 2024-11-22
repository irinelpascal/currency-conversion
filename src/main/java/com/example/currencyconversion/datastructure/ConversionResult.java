package com.example.currencyconversion.datastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ConversionResult {
    private Double conversionMultiple;
    private List<String> shortestPath;
    private String description;

    public ConversionResult(Double conversionMultiple, List<String> shortestPath, String description) {
        this.conversionMultiple = conversionMultiple;
        this.shortestPath = shortestPath;
        this.description = description;
    }
}
