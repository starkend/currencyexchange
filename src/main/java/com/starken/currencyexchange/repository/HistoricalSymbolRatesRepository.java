package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.HistoricalRates;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalSymbolRatesRepository extends ElasticsearchRepository<HistoricalRates, String> {

}
