package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.Symbol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository
        extends CrudRepository<Symbol, Long> {

}
