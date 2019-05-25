package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.service.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("application.properties")
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

    @Bean
    public EcbService ecbService() { return new EcbServiceImpl(); }

}
