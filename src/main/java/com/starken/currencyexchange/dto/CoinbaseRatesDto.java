package com.starken.currencyexchange.dto;

public class CoinbaseRatesDto {

    private SymbolRatesDto data;

    public SymbolRatesDto getData() {
        return data;
    }

    public void setData(SymbolRatesDto data) {
        this.data = data;
    }
}
