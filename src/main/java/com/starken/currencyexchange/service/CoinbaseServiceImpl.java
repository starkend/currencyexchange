package com.starken.currencyexchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.stream.Collectors;

public class CoinbaseServiceImpl implements CoinbaseService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.coinbase.url}")
    private String BASE_URL;

    @Override
    public CurrenciesDto getCurrencies() {
        String currenciesUrl = BASE_URL + "/currencies";

        HttpEntity<String> response = getStringResponse(currenciesUrl);

        return processCurrenciesResponse(response);
    }

    @Override
    public SymbolRatesDto getLatestSymbolRatesByBase(String usd) {
        String exchangeRatesUrl = BASE_URL + "/exchange-rates";

        HttpEntity<String> response = getStringResponse(exchangeRatesUrl);

        return processRatesResponse(response);
    }

    @Override
    public CoinbasePriceDto getBuyPrice(SymbolDto symbolDto) {
        String buyPriceUrl = BASE_URL
                + "/prices/" + symbolDto.getSymbol1() + "-"
                + symbolDto.getSymbol2() + "/buy";

        HttpEntity<String> response = getStringResponse(buyPriceUrl);

        return processPriceResponse(response);
    }

    @Override
    public CoinbasePriceDto getSellPrice(SymbolDto symbolDto) {
        String sellPriceUrl = BASE_URL
                + "/prices/" + symbolDto.getSymbol1() + "-"
                + symbolDto.getSymbol2() + "/sell";

        HttpEntity<String> response = getStringResponse(sellPriceUrl);

        return processPriceResponse(response);
    }

    @Override
    public CoinbasePriceDto getSpotPrice(SymbolDto symbolDto) {
        String spotPriceUrl = BASE_URL
                + "/prices/" + symbolDto.getSymbol1() + "-"
                + symbolDto.getSymbol2() + "/spot";

        HttpEntity<String> response = getStringResponse(spotPriceUrl);

        return processPriceResponse(response);
    }

    private CoinbasePriceDto processPriceResponse(HttpEntity<String> response) {
        CoinbasePriceDto coinbasePriceDto;

        if (response != null) {
            try {
                coinbasePriceDto = objectMapper.readValue(response.getBody(), CoinbasePriceDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

        return coinbasePriceDto;
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

    private CurrenciesDto processCurrenciesResponse(HttpEntity<String> response) {
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

        return currenciesDto;

    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }


    private HttpEntity<String> getStringResponse(String url) {
        HttpEntity<?> entity = new HttpEntity<>(buildHeaders());

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);
    }
}
