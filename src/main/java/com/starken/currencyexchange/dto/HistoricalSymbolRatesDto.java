package com.starken.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalSymbolRatesDto {

    private String base;
    private List<RatesDto> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<RatesDto> getRates() {
        return rates;
    }

    public void setRates(List<RatesDto> rates) {
        this.rates = rates;
    }

    @JsonProperty("rates")
    private void unpackNestedRates(Map<String, Map<String, String>> rates) {
        List<RatesDto> ratesDtoList = new ArrayList<>();

        for (String rateKey : rates.keySet()) {
            RatesDto ratesDto = new RatesDto();
            List<RateDto> rateDtos = new ArrayList<>();
            ratesDto.setDate(rateKey);
            for (String rateSymbolKey : rates.get(rateKey).keySet()) {
                RateDto rate = new RateDto();
                rate.setSymbol(rateSymbolKey);
                rate.setRate(String.valueOf(rates.get(rateKey).get(rateSymbolKey)));
                rateDtos.add(rate);
            }
            ratesDto.setRateList(rateDtos);
            ratesDtoList.add(ratesDto);
        }

        this.setRates(ratesDtoList);
    }

    static class RatesDto {
        private String date;
        private List<RateDto> rateList;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<RateDto> getRateList() {
            return rateList;
        }

        public void setRateList(List<RateDto> rateList) {
            this.rateList = rateList;
        }
    }

}
