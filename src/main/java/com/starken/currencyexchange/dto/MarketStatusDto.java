package com.starken.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketStatusDto implements Serializable {

    @JsonProperty("market_is_open")
    private boolean marketIsOpen;

    public boolean isMarketIsOpen() {
        return marketIsOpen;
    }

    public void setMarketIsOpen(boolean marketIsOpen) {
        this.marketIsOpen = marketIsOpen;
    }
}
