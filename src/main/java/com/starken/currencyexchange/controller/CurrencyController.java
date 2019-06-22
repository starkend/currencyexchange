package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.model.Currency;
import com.starken.currencyexchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currencyES")
public class CurrencyController {

    @Autowired
    CurrencyRepository currencyRepository;

    @RequestMapping("/all")
    public List<Currency> getAllCurrencies() {
        List<Currency> currencyList = new ArrayList<>();
        currencyRepository.findAll().forEach(currencyList::add);
        return currencyList;
    }
}
