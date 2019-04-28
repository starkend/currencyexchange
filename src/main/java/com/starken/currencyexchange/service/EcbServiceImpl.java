package com.starken.currencyexchange.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.QuoteDto;
import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.dto.SymbolRatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
            String responseString = response.getBody();

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
