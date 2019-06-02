package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.service.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("application.properties")
public class CurrencyexchangeConfiguration implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() { return new ObjectMapper();}

    @Bean
    public SymbolService symbolService() { return new SymbolServiceImpl(); }

    @Bean
    public EcbService ecbService() { return new EcbServiceImpl(); }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
