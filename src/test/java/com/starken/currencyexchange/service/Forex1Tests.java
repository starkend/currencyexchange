package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.dto.ConvertCurrencyDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import com.starken.currencyexchange.dto.SymbolDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyexchangeApplication.class, CurrencyexchangeConfiguration.class})
public class Forex1Tests {

    @Autowired
    private Forex1Service forex1Service;

    @Test
    public void whenGetAllSymbols_thenReturnSymbols() {
        List<SymbolDto> symbolDtos = forex1Service.getSymbols();

        assertFalse(symbolDtos.isEmpty());

    }

    @Test public void whenGetMarketStatus_thenReturnTrueOrFalse() {
        boolean marketStatus = forex1Service.getMarketStatus();

        assertTrue(marketStatus);
    }

    @Test
    public void whenConvertCurrency_thenReturnsCorrectValue() {
        ConvertCurrencyDto inboundCurrencyDto = new ConvertCurrencyDto();
        inboundCurrencyDto.setFrom("USD");
        inboundCurrencyDto.setTo("NZD");
        inboundCurrencyDto.setQuantity("100");

        CurrencyDto outputCurrencyDto = forex1Service.convertCurrency(inboundCurrencyDto);

        assertNotNull(outputCurrencyDto);

    }
}
