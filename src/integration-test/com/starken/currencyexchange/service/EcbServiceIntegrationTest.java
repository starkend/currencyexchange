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

import static org.assertj.core.api.Assertions.assertThat;
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
    public void whenGetLatestSymbols_theReturnSymbolStringList() {
        List<String> symbolList = null;

        symbolList = ecbService.getSymbolList();

        assertNotNull(symbolList);
        assertThat(symbolList.size() > 0);
    }
}
