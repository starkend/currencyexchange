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

    static class CurrencyDto {

        private String id;
        private String name;
        private String min_size;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMin_size() {
            return min_size;
        }

        public void setMin_size(String min_size) {
            this.min_size = min_size;
        }
    }
}
