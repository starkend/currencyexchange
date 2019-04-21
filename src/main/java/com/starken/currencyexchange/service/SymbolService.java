package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.ConvertCurrencyDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import com.starken.currencyexchange.dto.QuoteDto;
import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;

import java.util.List;
import java.util.Map;

public interface SymbolService {

    List<SymbolDto> getSymbols();

    List<SymbolDto> getSavedSymbols();

    boolean getMarketStatus();

    SymbolDto addSymbol(SymbolDto symbol);

    Symbol findBySymbolPair(String symbolPair);

    Map<String, List<String>> getSymbolTradingPairMap();

    QuoteDto retrieveQuote(SymbolDto symbolDto);

    CurrencyDto convertCurrency(ConvertCurrencyDto convertCurrencyDto);
}
