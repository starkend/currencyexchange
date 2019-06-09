package com.starken.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.CoinbaseRatesDto;
import com.starken.currencyexchange.dto.CurrenciesDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import com.starken.currencyexchange.dto.SymbolRatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public SymbolRatesDto getLatestSymbolRatesByBase(String usd) {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        String exchangeRatesUrl = BASE_URL + "/exchange-rates";

        HttpEntity<String> response = restTemplate.exchange(
                exchangeRatesUrl,
                HttpMethod.GET,
                entity,
                String.class);

        return processRatesResponse(response);
    }

    private SymbolRatesDto processRatesResponse(HttpEntity<String> response) {
        SymbolRatesDto symbolRatesDto;

        CoinbaseRatesDto coinbaseRatesDto;

        if (response != null) {
            try {
                coinbaseRatesDto = objectMapper.readValue(response.getBody(), CoinbaseRatesDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

        if (coinbaseRatesDto != null) {
            symbolRatesDto = coinbaseRatesDto.getData();
            symbolRatesDto.setRates(symbolRatesDto.getRates().stream().sorted().collect(Collectors.toList()));
            return symbolRatesDto;
        }

        return null;
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
