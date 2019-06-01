package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.*;
import com.starken.currencyexchange.model.Symbol;

import java.util.List;
import java.util.Map;

public interface SymbolService {

    List<SymbolDto> getSymbols();

    List<SymbolDto> getSavedSymbols();

//    boolean getMarketStatus();

    SymbolDto addSymbol(SymbolDto symbol);

    Symbol findBySymbolPair(String symbolPair);

    Map<String, List<String>> getSymbolTradingPairMap();

//    QuoteDto retrieveQuote(SymbolDto symbolDto);

    String convertCurrency(ConvertCurrencyDto convertCurrencyDto);

    List<String> getSymbolsList();

    RateDto retrieveRate(SymbolDto symbolDto);

    SymbolRatesDto getRatesForSymbol(String baseSymbol);
}
