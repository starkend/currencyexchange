package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;
import com.starken.currencyexchange.repository.SymbolRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
public class SymbolServiceImplIntegrationTest {

    @TestConfiguration
    static class SymbolServiceImplTestContextConfiguration {

        @Bean
        public SymbolService symbolService() {
            return new SymbolServiceImpl();
        }

    }

    @Autowired
    private SymbolService symbolService;

    @MockBean
    private SymbolRepository symbolRepository;

    @MockBean
    private EcbService ecbService;

    @Test
    public void whenValidSymbolPair_thenSymbolShouldBeFound() {
        Symbol symbol = new Symbol("USDAUD");

        Mockito.when(symbolRepository.findBySymbolPair(symbol.getSymbolPair()))
                .thenReturn(symbol);

        String symbolPair = "USDAUD";
        Symbol found = symbolService.findBySymbolPair(symbolPair);

        assertThat(found.getSymbolPair())
                .isEqualTo(symbolPair);
    }

    @Test
    public void whenGetSymbolsList_thenSymbolsListShouldBeFound() {
        List<String> symbolsList = new ArrayList<>();
        symbolsList.add("AUD");
        symbolsList.add("DKK");
        symbolsList.add("GBP");
        symbolsList.add("THB");

        Mockito.when(ecbService.getSymbolsList()).thenReturn(symbolsList);

        symbolsList = symbolService.getSymbolsList();

        assertFalse(symbolsList.isEmpty());
    }

    @Test
    public void whenGetSavedSymbols_thenSucceed() {
        List<Symbol> mockSymbolsList = new ArrayList<>();
        Symbol symbol1 = new Symbol("USDAUD", 1000L);
        Symbol symbol2 = new Symbol("AUDNZD", 2000L);
        Symbol symbol3 = new Symbol("TOPCAD", 3000L);
        mockSymbolsList.add(symbol1);
        mockSymbolsList.add(symbol2);
        mockSymbolsList.add(symbol3);

        Iterable<Symbol> symbolDtoIterable = mockSymbolsList;

        Mockito.when(symbolRepository.findAll())
                .thenReturn(symbolDtoIterable);

        List<SymbolDto> symbolDtos = symbolService.getSavedSymbols();
        assertFalse(symbolDtos.isEmpty());
    }

}
