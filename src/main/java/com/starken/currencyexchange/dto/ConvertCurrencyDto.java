package com.starken.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertCurrencyDto {
    private String fromCurrency;
    private String toCurrency;
    private String quantity;

    public ConvertCurrencyDto(String fromCurrency, String toCurrency, String quantity) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.quantity = quantity;
    }

    public ConvertCurrencyDto() {
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
