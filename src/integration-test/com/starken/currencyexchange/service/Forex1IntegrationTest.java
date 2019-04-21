package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.dto.ConvertCurrencyDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import com.starken.currencyexchange.dto.QuoteDto;
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
public class Forex1IntegrationTest {

    @Autowired
    private Forex1Service forex1Service;

    @Test
    public void whenGetSymbols_thenReturnSymbols() {
        List<SymbolDto> symbolDtos = forex1Service.getSymbols();

        assertFalse(symbolDtos.isEmpty());

    }

    @Test public void whenGetMarketStatus_thenReturnNotNullValue() {
        Boolean marketStatus = forex1Service.getMarketStatus();

        assertNotNull(marketStatus);
    }

    @Test
    public void whenConvertCurrency_thenReturnsPopulatedDtoObject() {
        ConvertCurrencyDto inboundCurrencyDto = new ConvertCurrencyDto();
        inboundCurrencyDto.setFromCurrency("USD");
        inboundCurrencyDto.setToCurrency("NZD");
        inboundCurrencyDto.setQuantity("100");

        CurrencyDto outputCurrencyDto = forex1Service.convertCurrency(inboundCurrencyDto);

        assertNotNull(outputCurrencyDto);

    }

    @Test
    public void whenGetQuote_thenReturnPopulatedQuoteDtoObject() {
        SymbolDto quoteSymbol = new SymbolDto("USDAUD");

        QuoteDto resultQuote = forex1Service.retrieveQuote(quoteSymbol);

        assertNotNull(resultQuote);
    }

}
