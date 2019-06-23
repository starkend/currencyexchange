package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.*;

import java.util.List;
import java.util.Map;

public interface EcbService {

    SymbolRatesDto getLatestSymbolRates();

    SymbolRatesDto getLatestSymbolRatesByBase(String base);

    List<String> getSymbolsList();

    Map<String, List<String>> getSymbolsMap();

    List<SymbolDto> getSymbolDtoList();

    RateDto getSingleLatestSymbolRateByBase(SymbolDto symbolDto);

    String convertCurrency(ConvertCurrencyDto convertCurrencyDto);

    HistoricalSymbolRatesDto getHistoricalRatesList();
}
