package com.example.currencyconversion.datastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Node {

    private String from;
    private String to;
    private double conversionMultiply;

    public Node(String from, String to, double conversionMultiply) {
        this.from = from;
        this.to = to;
        this.conversionMultiply = conversionMultiply;
    }
}
