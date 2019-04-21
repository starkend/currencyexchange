package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.ConvertCurrencyDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import com.starken.currencyexchange.dto.QuoteDto;
import com.starken.currencyexchange.dto.SymbolDto;

import java.util.List;

public interface Forex1Service {

    List<SymbolDto> getSymbols();

    Boolean getMarketStatus();

    CurrencyDto convertCurrency(ConvertCurrencyDto convertCurrencyDto);

    QuoteDto retrieveQuote(SymbolDto symbolDto);

}
