package com.starken.currencyexchange.repository;

import com.starken.currencyexchange.model.Symbol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SymbolRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    SymbolRepository symbolRepository;

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

    @Test
    public void whenDeleteSymbolPair_thenSucceed() {
        // given
        Symbol symbol = new Symbol();
        symbol.setSymbolPair("USDAUD");
        entityManager.persist(symbol);
        entityManager.flush();

        // when
        Symbol found = symbolRepository.findBySymbolPair(symbol.getSymbolPair());

        symbolRepository.delete(found);

        boolean deleteFailed = symbolRepository.existsById(found.getId());

        assertFalse(deleteFailed);
    }

    @Test
    public void whenDeleteSymbolPairById_thenSucceed() {
        // given
        Symbol symbol = new Symbol();
        symbol.setSymbolPair("USDAUD");
        Symbol savedSymbol = entityManager.persist(symbol);
        entityManager.flush();

        // when
        Optional<Symbol> found = symbolRepository.findById(savedSymbol.getId());

        if (found.isPresent()) {

            symbolRepository.deleteById(found.get().getId());

            boolean deleteFailed = symbolRepository.existsById(savedSymbol.getId());

            assertFalse(deleteFailed);
        } else {
            throw new AssertionError("Test Symbol Save did not succeed.  Never reached deletion test code");
        }
    }

}
