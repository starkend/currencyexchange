package com.starken.currencyexchange.service;

import com.starken.currencyexchange.model.Currency;
import com.starken.currencyexchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public List<Currency> findAll() {
        Iterable<Currency> currencyIterable = currencyRepository.findAll();
        List<Currency> currencies;

        currencies = StreamSupport.stream(currencyIterable.spliterator(), false)
                .map(c -> new Currency(c.getId(), c.getName()))
                .collect(Collectors.toList());

        return currencies;
    }
}
