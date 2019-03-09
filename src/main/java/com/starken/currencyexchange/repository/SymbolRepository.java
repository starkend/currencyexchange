package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.Symbol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface SymbolRepository
        extends CrudRepository<Symbol, Long> {

}
