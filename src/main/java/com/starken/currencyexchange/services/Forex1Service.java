package com.starken.currencyexchange.services;

import com.starken.currencyexchange.forex1.dto.Symbol;
import java.util.List;

public interface Forex1Service {

    List<Symbol> getSymbols();
}
