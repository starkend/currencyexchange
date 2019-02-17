package com.starken.currencyexchange.forex1;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.CurrencyexchangeConfiguration;
import com.starken.currencyexchange.forex1.dto.Symbol;
import com.starken.currencyexchange.services.Forex1Service;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CurrencyexchangeApplication.class, CurrencyexchangeConfiguration.class})
public class Forex1Tests {

    @Autowired
    private Forex1Service forex1Service;

    @Test
    public void whenGetAllSymbols_thenReturnSymbols() {
        List<Symbol> symbols = forex1Service.getSymbols();

        assertFalse(symbols.isEmpty());

    }
}
