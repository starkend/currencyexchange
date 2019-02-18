package com.starken.currencyexchange.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.forex1.dto.MarketStatus;
import com.starken.currencyexchange.forex1.dto.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Service
public class Forex1ServiceImpl implements Forex1Service {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.forex1.url.symbols}")
    private String FOREX1_URL_SYMBOLS;

    @Value("${service.forex1.url.market_status}")
    private String FOREX1_URL_MARKET_STATUS;

    @Value("${service.forex1.api_key}")
    private String api_key;

    @Override
    public List<Symbol> getSymbols() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        HttpEntity<String> response = getStringHttpEntity(getApiKeyUriComponentsBuilder(FOREX1_URL_SYMBOLS), entity);

        if (response != null) {
            String responseString = response.getBody();
            List<Symbol> symbolList = null;
            try {
                symbolList = objectMapper.readValue(response.getBody(), new TypeReference<List<Symbol>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }

            return symbolList;
        } else {
            return null;
        }

    }


    @Override
    public boolean getMarketStatus() {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        HttpEntity<String> response = getStringHttpEntity(getApiKeyUriComponentsBuilder(FOREX1_URL_MARKET_STATUS), entity);

        if (response != null) {
            String responseString = response.getBody();
            MarketStatus marketStatus = null;

            try {
                marketStatus = objectMapper.readValue(response.getBody(), MarketStatus.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return marketStatus.isMarketIsOpen();
        } else {
            return false;
        }
    }

    private UriComponentsBuilder getApiKeyUriComponentsBuilder(String url) {
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("api_key", api_key);

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
