package com.starken.currencyexchange.service;

import com.starken.currencyexchange.model.Currency;

import java.util.List;

public interface CurrencyService {
    Currency save(Currency currency);

    List<Currency> findAll();
}
