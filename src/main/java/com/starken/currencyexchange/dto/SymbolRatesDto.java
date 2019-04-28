package com.starken.currencyexchange.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolRatesDto {
    private String base;
    private Date date;
    private List<RateDto> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("rates")
    private void unpackNestedRates(Map<String, Object> rates) {
        List<RateDto> rateDtos = new ArrayList<>();
        for (String rateKey : rates.keySet()) {
            RateDto rate = new RateDto();
            rate.setSymbol(rateKey);
            rate.setRate(String.valueOf(rates.get(rateKey)));
            rateDtos.add(rate);
        }

        this.setRates(rateDtos);
    }

    public List<RateDto> getRates() {
        return rates;
    }

    public void setRates(List<RateDto> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "SymbolRatesDto{" +
                "base='" + base + '\'' +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }


}
