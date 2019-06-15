package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.CoinbasePriceDto;
import com.starken.currencyexchange.dto.CurrencyDto;
import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.dto.SymbolRatesDto;

import java.util.List;

public interface CoinbaseService {
    public List<CurrencyDto> getCurrencies();

    SymbolRatesDto getLatestSymbolRatesByBase(String usd);

    CoinbasePriceDto getBuyPrice(SymbolDto symbolDto);
}
