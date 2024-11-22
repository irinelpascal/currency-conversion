package com.example.currencyconversion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyConversionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyConversionApplicationTests {

    @Test
    void contextLoads() {
    }

}
