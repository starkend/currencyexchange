package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.HistoricalRates;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalSymbolRatesRepository extends ElasticsearchRepository<HistoricalRates, String> {
    public List<HistoricalRates> findByBase(String base);
}
