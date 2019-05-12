package com.starken.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.SymbolRatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EcbServiceImpl implements EcbService {

    private static final String BASE_QUERY_PARAM = "base";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.ecb.url.latest}")
    private String URL_LATEST;


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

    @Override
    public SymbolRatesDto getLatestSymbolRates() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        HttpEntity<String> response = restTemplate.exchange(
                URL_LATEST,
                HttpMethod.GET,
                entity,
                String.class);

        SymbolRatesDto symbolRatesDto = processSymbolRatesResponse(response);

        return symbolRatesDto;
    }

    @Override
    public SymbolRatesDto getLatestSymbolRatesByBase(String base) {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        MultiValueMap<String, String> queryParams = getLatestSymbolRatesByBaseQueryParams(base);

        HttpEntity<String> response = getStringHttpEntity(getUriComponentsBuilderWithParams(URL_LATEST, queryParams), entity);

        SymbolRatesDto symbolRatesDto = processSymbolRatesResponse(response);

        return symbolRatesDto;
    }

    private SymbolRatesDto processSymbolRatesResponse(HttpEntity<String> response) {
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

        symbolRatesDto.setRates(symbolRatesDto.getRates().stream().sorted().collect(Collectors.toList()));

        return symbolRatesDto;
    }

    private MultiValueMap<String, String> getLatestSymbolRatesByBaseQueryParams(String base) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.put(BASE_QUERY_PARAM, Collections.singletonList(base));

        return queryParams;
    }

    private UriComponentsBuilder getUriComponentsBuilderWithParams(String url, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromUriString(url)
                .queryParams(params);
    }

    private HttpEntity<String> getStringHttpEntity(UriComponentsBuilder builder, HttpEntity<?> entity) {
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
