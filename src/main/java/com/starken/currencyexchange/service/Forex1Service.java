package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolDto;

import java.util.List;

public interface Forex1Service {

    List<SymbolDto> getSymbols();

    boolean getMarketStatus();


}
