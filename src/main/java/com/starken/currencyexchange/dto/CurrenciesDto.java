package com.starken.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrenciesDto {

    private List<CurrencyDto> data;

    public List<CurrencyDto> getData() {
        return data;
    }

    public void setData(List<CurrencyDto> data) {
        this.data = data;
    }
}
