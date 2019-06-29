package com.starken.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EcbServiceImpl implements EcbService {

    private static final String BASE_QUERY_PARAM = "base";
    private static final String START_AT_QUERY_PARAM = "start_at";
    private static final String END_AT_QUERY_PARAM = "end_at";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.ecb.url.latest}")
    private String URL_LATEST;

    @Value("${service.ecb.url.history}")
    private String URL_HISTORY;

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
            List<String> predicateList = new ArrayList<>(symbolsList);

            predicateList.remove(symbol);
            symbolsMap.put(symbol, predicateList);
        }

        return symbolsMap;
    }

    @Override
    public List<SymbolDto> getSymbolDtoList() {
        List<SymbolDto> symbolDtos = new ArrayList<>();

        List<String> symbolsList = getSymbolsList();

        if (symbolsList.isEmpty()) {
            return null;
        }

        for (String symbol1 : symbolsList) {
            for (String symbol2 : symbolsList) {
                if (!(symbol1.equalsIgnoreCase(symbol2))) {
                    SymbolDto newSymbolDto = new SymbolDto(symbol1 + symbol2);
                    symbolDtos.add(newSymbolDto);
                }
            }
        }

        return symbolDtos;
    }

    @Override
    public RateDto getSingleLatestSymbolRateByBase(SymbolDto symbolDto) {
        SymbolRatesDto symbolRatesDto = getLatestSymbolRatesByBase(symbolDto.getSymbol1());

        if (symbolRatesDto == null) {
            return null;
        }

        for (RateDto rate : symbolRatesDto.getRates()) {
            if (symbolDto.getSymbol2().equalsIgnoreCase(rate.getSymbol())) {
                return rate;
            }
        }
        return null;
    }

    @Override
    public String convertCurrency(ConvertCurrencyDto convertCurrencyDto) {

        RateDto rateDto = getSingleLatestSymbolRateByBase(
                new SymbolDto(convertCurrencyDto.getFromCurrency(), convertCurrencyDto.getToCurrency()));

        BigDecimal rate = new BigDecimal(rateDto.getRate());
        BigDecimal quantity = new BigDecimal(convertCurrencyDto.getQuantity());

        BigDecimal total = rate.multiply(quantity);

        return total.toString();
    }

    @Override
    public HistoricalSymbolRatesDto getHistoricalRates() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);

        MultiValueMap<String, String> queryParams = getHistoricalRatesParams("", startDate.toString(), endDate.toString());

        HttpEntity<String> response =
                getStringHttpEntity(getUriComponentsBuilderWithParams(URL_HISTORY, queryParams), entity);

        return processHistoricalRates(response);
    }

    @Override
    public HistoricalSymbolRatesDto getHistoricalRatesByBase(String base) {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);

        MultiValueMap<String, String> queryParams = getHistoricalRatesParams(base, startDate.toString(), endDate.toString());

        HttpEntity<String> response =
                getStringHttpEntity(getUriComponentsBuilderWithParams(URL_HISTORY, queryParams), entity);

        return processHistoricalRates(response);
    }

    private HistoricalSymbolRatesDto processHistoricalRates(HttpEntity<String> response) {
        HistoricalSymbolRatesDto historicalSymbolRatesDto;

        if (response != null) {
            try {
                historicalSymbolRatesDto = objectMapper.readValue(response.getBody(), HistoricalSymbolRatesDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

        historicalSymbolRatesDto.setRates(historicalSymbolRatesDto.getRates().stream().sorted().collect(Collectors.toList()));

        return historicalSymbolRatesDto;
    }

    private MultiValueMap<String, String> getHistoricalRatesParams(String base, String startDate, String endDate) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        if (!StringUtils.isEmpty(base)) {
            queryParams.put(BASE_QUERY_PARAM, Collections.singletonList(base));
        }
        queryParams.put(START_AT_QUERY_PARAM, Collections.singletonList(startDate));
        queryParams.put(END_AT_QUERY_PARAM, Collections.singletonList(endDate));
        return queryParams;
    }


    @Override
    public SymbolRatesDto getLatestSymbolRates() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        HttpEntity<String> response = restTemplate.exchange(
                URL_LATEST,
                HttpMethod.GET,
                entity,
                String.class);

        return processSymbolRatesResponse(response);
    }

    @Override
    public SymbolRatesDto getLatestSymbolRatesByBase(String base) {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        MultiValueMap<String, String> queryParams = getLatestSymbolRatesByBaseQueryParams(base);

        HttpEntity<String> response = getStringHttpEntity(getUriComponentsBuilderWithParams(URL_LATEST, queryParams), entity);

        return processSymbolRatesResponse(response);
    }

    private SymbolRatesDto processSymbolRatesResponse(HttpEntity<String> response) {
        SymbolRatesDto symbolRatesDto;

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
