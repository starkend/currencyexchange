package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;

import java.util.List;

public interface SymbolService {

    public List<SymbolDto> getSymbols();

    public List<SymbolDto> getSavedSymbols();

    public boolean getMarketStatus();

    public SymbolDto addSymbol(SymbolDto symbol);
}
