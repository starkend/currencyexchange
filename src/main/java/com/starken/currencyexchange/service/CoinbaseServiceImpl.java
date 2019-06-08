package com.starken.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.CurrenciesDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public class CoinbaseServiceImpl implements CoinbaseService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.coinbase.url}")
    private String BASE_URL;

    @Override
    public List<CurrencyDto> getCurrencies() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        String currenciesUrl = BASE_URL + "/currencies";

        HttpEntity<String> response = restTemplate.exchange(
                currenciesUrl,
                HttpMethod.GET,
                entity,
                String.class);

        return processCurrenciesResponse(response);
    }

    private List<CurrencyDto> processCurrenciesResponse(HttpEntity<String> response) {
        CurrenciesDto currenciesDto;

        if (response != null) {
            try {
                currenciesDto = objectMapper.readValue(response.getBody(), CurrenciesDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

        return currenciesDto.getData();

    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
