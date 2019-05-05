package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.dto.SymbolRatesDto;
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
    public void whenGetLatestSymbolRates_thenReturnsPopulatedSymbolRatesDto() {
        SymbolRatesDto symbolRatesDto = ecbService.getLatestSymbolRates();

        assertNotNull(symbolRatesDto);
        assertNotNull(symbolRatesDto.getRates());
    }

    @Test
    public void whenGetSymbolsList_thenReturnsPopulatedSymbolList() {
        List<String> symbolList = null;

        symbolList = ecbService.getSymbolsList();

        assertNotNull(symbolList);
        assertFalse(symbolList.isEmpty());
    }

    @Test
    public void whenGetSymbolsMap_thenReturnPopulatedSymbolsMap() {
        Map<String, List<String>> symbolsMap = null;

        symbolsMap = ecbService.getSymbolsMap();

        assertNotNull(symbolsMap);
        assertFalse(symbolsMap.isEmpty());
    }
}
