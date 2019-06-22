package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.model.Currency;
import com.starken.currencyexchange.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencyES")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping("/all")
    public List<Currency> getAllCurrencies() {
        return currencyService.findAll();
    }
}
