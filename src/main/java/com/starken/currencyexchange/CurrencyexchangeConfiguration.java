package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.services.Forex1Service;
import com.starken.currencyexchange.services.Forex1ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CurrencyexchangeConfiguration {

    @Bean
    public Forex1Service forex1Service(RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new Forex1ServiceImpl(restTemplate, objectMapper);
    }

}
