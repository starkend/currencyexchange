package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolRatesDto;

import java.util.List;
import java.util.Map;

public interface EcbService {

    SymbolRatesDto getLatestSymbolRates();

    List<String> getSymbolsList();

    Map<String, List<String>> getSymbolsMap();
}
