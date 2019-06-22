package com.starken.currencyexchange.service;

import com.starken.currencyexchange.model.Currency;
import com.starken.currencyexchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }
}
