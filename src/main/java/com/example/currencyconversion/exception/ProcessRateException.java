package com.example.currencyconversion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProcessRateException extends RuntimeException {
    public ProcessRateException(String message) {
        super(message);
    }
}
