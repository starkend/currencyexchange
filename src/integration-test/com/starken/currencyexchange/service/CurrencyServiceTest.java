package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.model.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyexchangeApplication.class)
public class CurrencyServiceTest {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void whenSaveCurrency_thenReturnSuccessfullySavedCurrency() {
        Currency currency = new Currency("2", "AUD");
        Currency returnCurrency = currencyService.save(currency);

        assertNotNull(returnCurrency);

    }

    @Test
    public void whenFindAllCurrencies_thenReturnCurrenciesSuccessfully() {
        Currency testCurrency = currencyService.save(new Currency("9999", "BTC"));

        assertNotNull(testCurrency);

        List<Currency> currencyList = currencyService.findAll();

        assertNotNull(currencyList);
        assertFalse(currencyList.isEmpty());
    }


}
