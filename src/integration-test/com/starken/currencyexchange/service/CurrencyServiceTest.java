package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.model.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyexchangeApplication.class)
public class CurrencyServiceTest {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void whenSaveBook_thenReturnSuccess() {
        Currency currency = new Currency("1", "USD");
        Currency returnCurrency = currencyService.save(currency);

        assertNotNull(returnCurrency);

    }

}
