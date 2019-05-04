package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolRatesDto;

import java.util.List;

public interface EcbService {

    SymbolRatesDto getLatestSymbolRates();

    List<String> getSymbolList();
}
