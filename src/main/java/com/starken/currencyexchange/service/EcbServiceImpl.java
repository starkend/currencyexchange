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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getSymbolsList() {
        List<String> symbolList = new ArrayList<>();

        SymbolRatesDto symbolRatesDtos = getLatestSymbolRates();

        if (symbolRatesDtos == null) {
            return null;
        }

        symbolList.add(symbolRatesDtos.getBase());
        symbolRatesDtos.getRates().forEach(rateDto -> symbolList.add(rateDto.getSymbol()));

        return symbolList.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Map<String, List<String>> getSymbolsMap() {
        Map<String, List<String>> symbolsMap = new TreeMap<>();

        List<String> symbolsList = getSymbolsList();

        if (symbolsList.isEmpty()) {
            return null;
        }

        for (String symbol : symbolsList) {
            List<String> predicateList = new ArrayList<>();
            predicateList.addAll(symbolsList);

            predicateList.remove(symbol);

            symbolsMap.put(symbol, predicateList);
        }

        return symbolsMap;
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
