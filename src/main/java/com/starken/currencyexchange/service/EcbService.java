package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolRatesDto;

public interface EcbService {

    SymbolRatesDto getLatestSymbolRates();

}
