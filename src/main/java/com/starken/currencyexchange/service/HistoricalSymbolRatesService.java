package com.starken.currencyexchange.service;

import com.starken.currencyexchange.model.HistoricalRates;

import java.util.List;

public interface HistoricalSymbolRatesService {

    HistoricalRates save(HistoricalRates historicalRates);

    List<HistoricalRates> findAll();

    List<HistoricalRates> findByBase(String base);
}
