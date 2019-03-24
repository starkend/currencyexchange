package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;

import java.util.List;

public interface SymbolService {

    List<SymbolDto> getSymbols();

    List<SymbolDto> getSavedSymbols();

    boolean getMarketStatus();

    SymbolDto addSymbol(SymbolDto symbol);

    Symbol findBySymbolPair(String symbolPair);
}
