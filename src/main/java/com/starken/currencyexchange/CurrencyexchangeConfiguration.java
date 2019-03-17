package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.repository.SymbolRepository;
import com.starken.currencyexchange.service.Forex1Service;
import com.starken.currencyexchange.service.Forex1ServiceImpl;
import com.starken.currencyexchange.service.SymbolService;
import com.starken.currencyexchange.service.SymbolServiceImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("ce-dev.properties")
public class CurrencyexchangeConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() { return new ObjectMapper();}

    @Bean
    public Forex1Service forex1Service() {
        return new Forex1ServiceImpl();
    }

    @Bean
    public SymbolService symbolService() { return new SymbolServiceImpl(); }

}
