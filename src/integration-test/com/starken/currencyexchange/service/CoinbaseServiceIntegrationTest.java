package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.dto.CurrencyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyexchangeApplication.class, CurrencyexchangeConfiguration.class})
public class CoinbaseServiceIntegrationTest {

    @Autowired
    CoinbaseService coinbaseService;


    @Test
    public void whenGetCurrencies_thenReturnsPopulatedCurrenciesList() {
        List<CurrencyDto> currencyDtoList = coinbaseService.getCurrencies();

        assertNotNull(currencyDtoList);
        assertFalse(currencyDtoList.isEmpty());
    }

}
