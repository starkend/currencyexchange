package com.starken.currencyexchange.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class Forex1ServiceImpl implements Forex1Service {

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Value("${service.forex1.url.symbols}")
//    private String URL_SYMBOLS;
//
//    @Value("${service.forex1.url.market_status}")
//    private String URL_MARKET_STATUS;
//
//    @Value("${service.forex1.url.convert}")
//    private String URL_CONVERT;
//
//    @Value("${service.forex1.url.quote}")
//    private String URL_QUOTE;
//
//    @Value("${service.forex1.api_key}")
//    private String api_key;
//
//    @Override
//    public List<SymbolDto> getSymbols() {
//        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());
//
//        HttpEntity<String> response = getStringHttpEntity(getUriComponentsBuilderWithParams(URL_SYMBOLS), entity);
//        List<SymbolDto> symbolDtoList = null;
//
//        if (response != null) {
//            String responseString = response.getBody();
//
//            try {
//                symbolDtoList = objectMapper.readValue(response.getBody(), new TypeReference<List<SymbolDto>>(){});
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (symbolDtoList != null) {
//            Collections.sort(symbolDtoList);
//        }
//
//        return symbolDtoList;
//    }
//
//
//    @Override
//    public Boolean getMarketStatus() {
//        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());
//
//        HttpEntity<String> response = getStringHttpEntity(getUriComponentsBuilderWithParams(URL_MARKET_STATUS), entity);
//
//        if (response != null) {
//            String responseString = response.getBody();
//            MarketStatusDto marketStatusDto = null;
//
//            try {
//                marketStatusDto = objectMapper.readValue(response.getBody(), MarketStatusDto.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return marketStatusDto.isMarketIsOpen();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public CurrencyDto convertCurrency(ConvertCurrencyDto convertCurrencyDto) {
//        CurrencyDto currencyDto = new CurrencyDto();
//
//        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());
//
//        MultiValueMap<String, String> queryParams = getConvertCurrencyQueryParams(convertCurrencyDto);
//
//        HttpEntity<String> response = getStringHttpEntity(getUriComponentsBuilderWithParams(URL_CONVERT, queryParams), entity);
//
//        if (response != null) {
//            String responseString = response.getBody();
//
//            try {
//                currencyDto = objectMapper.readValue(response.getBody(), CurrencyDto.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return currencyDto;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public QuoteDto retrieveQuote(SymbolDto symbolDto) {
//        List<QuoteDto> quoteDtoList = null;
//
//        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());
//
//        MultiValueMap<String, String> queryParams = getQuoteQueryParams(symbolDto);
//
//        HttpEntity<String> response = getStringHttpEntity(getUriComponentsBuilderWithParams(URL_QUOTE, queryParams), entity);
//
//        if (response != null) {
//            String responseString = response.getBody();
//
//            try {
//                quoteDtoList = Arrays.asList(objectMapper.readValue(response.getBody(), QuoteDto[].class));
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            return null;
//        }
//
//        return quoteDtoList.isEmpty() ? null : quoteDtoList.get(0);
//    }
//
//    private MultiValueMap<String, String> getConvertCurrencyQueryParams(ConvertCurrencyDto convertCurrencyDto) {
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//
//        queryParams.put("from", Collections.singletonList(convertCurrencyDto.getFromCurrency()));
//        queryParams.put("to", Collections.singletonList(convertCurrencyDto.getToCurrency()));
//        queryParams.put("quantity", Collections.singletonList(convertCurrencyDto.getQuantity()));
//        queryParams.put("api_key", Collections.singletonList(api_key));
//        return queryParams;
//    }
//
//    private MultiValueMap<String, String> getQuoteQueryParams(SymbolDto symbolDto) {
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//
//        queryParams.put("pairs", Collections.singletonList(symbolDto.getSymbolPair()));
//        queryParams.put("api_key", Collections.singletonList(api_key));
//        return queryParams;
//    }
//
//
//    private UriComponentsBuilder getUriComponentsBuilderWithParams(String url) {
//        return UriComponentsBuilder.fromUriString(url)
//                .queryParam("api_key", api_key);
//
//    }
//
//    private UriComponentsBuilder getUriComponentsBuilderWithParams(String url, MultiValueMap<String, String> params) {
//        return UriComponentsBuilder.fromUriString(url)
//                .queryParams(params);
//    }
//
//    private HttpEntity<String> getStringHttpEntity(UriComponentsBuilder builder, HttpEntity<?> entity) {
//        return restTemplate.exchange(
//                builder.toUriString(),
//                HttpMethod.GET,
//                entity,
//                String.class);
//    }
//
//    private HttpHeaders buildHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//        return headers;
//    }

}
