package com.starken.currencyexchange.forex1;

import com.starken.currencyexchange.com.starken.currencyexchange.forex1.dto.Symbol;
import com.starken.currencyexchange.services.Forex1Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Forex1Tests {

    @Autowired
    private Forex1Service forex1Service;

    @Test
    public void whenGetAllSymbols_thenReturnSymbols() {
        List<Symbol> symbols = forex1Service.getSymbols();


    }
}
