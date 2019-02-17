package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.services.Forex1Service;
import com.starken.currencyexchange.services.Forex1ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("ce-dev.properties")
public class CurrencyexchangeConfiguration {

    @Bean
    public Forex1Service forex1Service() {
        return new Forex1ServiceImpl();
    }

}
