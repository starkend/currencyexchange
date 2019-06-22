package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.Currency;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends ElasticsearchRepository<Currency, String> {

}
