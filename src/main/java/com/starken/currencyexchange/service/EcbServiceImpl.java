package com.starken.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.SymbolRatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class EcbServiceImpl implements EcbService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.ecb.url.latest}")
    private String URL_LATEST;

    @Override
    public SymbolRatesDto getLatestSymbolRates() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        HttpEntity<String> response = restTemplate.exchange(
                URL_LATEST,
                HttpMethod.GET,
                entity,
                String.class);

        SymbolRatesDto symbolRatesDto = null;

        if (response != null) {
            try {
                symbolRatesDto = objectMapper.readValue(response.getBody(), SymbolRatesDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

        return symbolRatesDto;
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
