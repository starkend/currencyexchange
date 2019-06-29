package com.starken.currencyexchange.model;

import com.starken.currencyexchange.dto.RatesDto;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document(indexName = "historicalratesindex", type = "historicalrates")
public class HistoricalRates implements Serializable {

    @Id
    private String id;

    private String base;

    private List<RatesDto> rates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public HistoricalRates(String id, String base, List<RatesDto> rates) {
        this.id = id;
        this.base = base;
        this.rates = rates;
    }

    public HistoricalRates() {}

}
