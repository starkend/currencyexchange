package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.CurrencyDto;

import java.util.List;

public interface CoinbaseService {
    public List<CurrencyDto> getCurrencies();
}
