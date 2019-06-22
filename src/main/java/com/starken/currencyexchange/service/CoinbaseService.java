package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.CoinbasePriceDto;
import com.starken.currencyexchange.dto.CurrenciesDto;
import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.dto.SymbolRatesDto;

public interface CoinbaseService {
    CurrenciesDto getCurrencies();

    SymbolRatesDto getLatestSymbolRatesByBase(String usd);

    CoinbasePriceDto getBuyPrice(SymbolDto symbolDto);

    CoinbasePriceDto getSellPrice(SymbolDto symbolDto);

    CoinbasePriceDto getSpotPrice(SymbolDto symbolDto);
}
