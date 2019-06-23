package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyexchangeApplication.class, CurrencyexchangeConfiguration.class})
public class EcbServiceIntegrationTest {

    @Autowired
    EcbService ecbService;


    @Test
    public void whenGetLatestSymbolRatesByBase_thenReturnsPopulatedSymbolRatesDto() {
        SymbolRatesDto symbolRatesDto = ecbService.getLatestSymbolRatesByBase("USD");

        assertNotNull(symbolRatesDto);
        assertNotNull(symbolRatesDto.getRates());
    }

    @Test
    public void whenGetSingleLatestSymbolRateByBase_thenReturnsPopulatedRateDto() {
        String symbol = "USDAUD";
        RateDto rateDto = ecbService.getSingleLatestSymbolRateByBase(new SymbolDto(symbol));

        assertNotNull(rateDto);
    }

    @Test
    public void whenGetLatestSymbolRates_thenReturnsPopulatedSymbolRatesDto() {
        SymbolRatesDto symbolRatesDto = ecbService.getLatestSymbolRates();

        assertNotNull(symbolRatesDto);
        assertNotNull(symbolRatesDto.getRates());
    }

    @Test
    public void whenGetSymbolsList_thenReturnsPopulatedSymbolList() {
        List<String> symbolList;

        symbolList = ecbService.getSymbolsList();

        assertNotNull(symbolList);
        assertFalse(symbolList.isEmpty());
    }

    @Test
    public void whenGetSymbolsMap_thenReturnPopulatedSymbolsMap() {
        Map<String, List<String>> symbolsMap;

        symbolsMap = ecbService.getSymbolsMap();

        assertNotNull(symbolsMap);
        assertFalse(symbolsMap.isEmpty());
    }

    @Test
    public void whenGetSymbolDtoList_thenReturnPopulatedSymbolDtoList() {
        List<SymbolDto> symbolDtos;

        symbolDtos = ecbService.getSymbolDtoList();

        assertNotNull(symbolDtos);
        assertFalse(symbolDtos.isEmpty());
    }

    @Test
    public void whenGetConvertCurrencyDto_thenReturnConvertedCurrencyString() {
        ConvertCurrencyDto convertCurrencyDto = new ConvertCurrencyDto(
                "AUD","USD","5.50");

        String convertedCurrencyString = ecbService.convertCurrency(convertCurrencyDto);

        assertFalse(convertedCurrencyString.isBlank());
    }

    @Test
    public void whenGetHistoricalRates_thenReturnPopulatedHistoricalRatesSymbolDtoList() {
        HistoricalSymbolRatesDto historicalSymbolRatesDto;

        historicalSymbolRatesDto = ecbService.getHistoricalRatesList();

        assertNotNull(historicalSymbolRatesDto);
        assertNotNull(historicalSymbolRatesDto.getRates());
    }
}
