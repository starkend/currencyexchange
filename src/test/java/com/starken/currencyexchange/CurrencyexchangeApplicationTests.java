package com.starken.currencyexchange;

import com.starken.currencyexchange.controller.SymbolController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CurrencyexchangeApplication.class, CurrencyexchangeConfiguration.class})
public class CurrencyexchangeApplicationTests {

    @Autowired
    private SymbolController controller;

    @Test
    public void contexLoads() {
        assertThat(controller).isNotNull();
    }

}
