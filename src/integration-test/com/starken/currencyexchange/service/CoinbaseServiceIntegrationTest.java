package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.dto.CoinbasePriceDto;
import com.starken.currencyexchange.dto.CurrenciesDto;
import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.dto.SymbolRatesDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyexchangeApplication.class, CurrencyexchangeConfiguration.class})
public class CoinbaseServiceIntegrationTest {

    @Autowired
    CoinbaseService coinbaseService;


    @Test
    public void whenGetCurrencies_thenReturnsPopulatedCurrenciesList() {
        CurrenciesDto currenciesDto = coinbaseService.getCurrencies();

        assertNotNull(currenciesDto);
        assertFalse(currenciesDto.getData().isEmpty());
    }

    @Test
    public void whenGetRates_thenReturnPopulatedRates() {
        SymbolRatesDto symbolRatesDto = coinbaseService.getLatestSymbolRatesByBase("USD");

        assertNotNull(symbolRatesDto);
        assertNotNull(symbolRatesDto.getRates());
    }

    @Test
    public void whenGetBuyPriceOfPair_thenReturnBuyPriceAmount() {
        SymbolDto symbolDto = new SymbolDto("BTCUSD");
        CoinbasePriceDto coinbasePriceDto = coinbaseService.getBuyPrice(symbolDto);

        assertNotNull(coinbasePriceDto);
    }

    @Test
    public void whenGetSellPriceOfPair_thenReturnSellPriceAmount() {
        SymbolDto symbolDto = new SymbolDto("BTCUSD");
        CoinbasePriceDto coinbasePriceDto = coinbaseService.getSellPrice(symbolDto);

        assertNotNull(coinbasePriceDto);
    }

    @Test
    public void whenGetSpotPriceOfPair_thenReturnSpotPriceAmount() {
        SymbolDto symbolDto = new SymbolDto("BTCUSD");
        CoinbasePriceDto coinbasePriceDto = coinbaseService.getSpotPrice(symbolDto);

        assertNotNull(coinbasePriceDto);
    }

}
