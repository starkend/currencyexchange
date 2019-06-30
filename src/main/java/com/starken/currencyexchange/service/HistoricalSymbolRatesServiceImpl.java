package com.starken.currencyexchange.service;

import com.starken.currencyexchange.model.HistoricalRates;
import com.starken.currencyexchange.repository.HistoricalSymbolRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HistoricalSymbolRatesServiceImpl implements HistoricalSymbolRatesService {

    @Autowired
    HistoricalSymbolRatesRepository historicalSymbolRatesRepository;

    @Override
    public HistoricalRates save(HistoricalRates historicalRates) {
        return historicalSymbolRatesRepository.save(historicalRates);
    }

    @Override
    public List<HistoricalRates> findAll() {
        Iterable<HistoricalRates> historicalRatesIterable = historicalSymbolRatesRepository.findAll();
        List<HistoricalRates> historicalRates;

        historicalRates = StreamSupport.stream(historicalRatesIterable.spliterator(), false)
                .collect(Collectors.toList());

        return historicalRates;
    }

    @Override
    public List<HistoricalRates> findByBase(String base) {
        Iterable<HistoricalRates> historicalRatesIterable = historicalSymbolRatesRepository.findByBase(base);
        List<HistoricalRates> historicalRates;

        historicalRates = StreamSupport.stream(historicalRatesIterable.spliterator(), false)
                .collect(Collectors.toList());

        return historicalRates;
    }
}
