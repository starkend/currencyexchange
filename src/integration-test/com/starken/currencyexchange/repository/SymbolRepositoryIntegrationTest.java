package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.Symbol;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SymbolRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    SymbolRepository symbolRepository;

    //TODO Fix SymbolRepository test
    @Ignore
    @Test
    public void whenFindBySymbolPair_thenReturnSymbol() {
        // given
        Symbol symbol = new Symbol();
        symbol.setSymbolPair("USDAUD");
        entityManager.persist(symbol);
        entityManager.flush();

        // when
        Symbol found = symbolRepository.findBySymbolPair(symbol.getSymbolPair());

        // then
        assertThat(symbol.getSymbolPair()).isEqualTo(found.getSymbolPair());

    }

}
