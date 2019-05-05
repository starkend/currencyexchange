package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;
import com.starken.currencyexchange.repository.SymbolRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
    private Forex1Service forex1Service;

    @Before
    public void setUp() {
        Symbol symbol = new Symbol("USDAUD");

        Mockito.when(symbolRepository.findBySymbolPair(symbol.getSymbolPair()))
                .thenReturn(symbol);
    }

    @Test
    public void whenValidSymbolPair_thenSymbolShouldBeFound() {
        String symbolPair = "USDAUD";
        Symbol found = symbolService.findBySymbolPair(symbolPair);

        assertThat(found.getSymbolPair())
                .isEqualTo(symbolPair);
    }

    @Test
    public void whenGetSymbolTradingPairMap_thenMapShouldBeFound() {
        Map<String, List<String>> symbolMap = null;
        List<SymbolDto> symbolDtoList = new ArrayList<>();
        symbolDtoList.add(new SymbolDto("AUDUSD"));
        symbolDtoList.add(new SymbolDto("USDAUD"));
        symbolDtoList.add(new SymbolDto("AUDJPY"));
        symbolDtoList.add(new SymbolDto("USDBTC"));
        symbolDtoList.add(new SymbolDto("BTCAUD"));

        Mockito.when(forex1Service.getSymbols()).thenReturn(symbolDtoList);

        symbolMap = symbolService.getSymbolTradingPairMap();

        assertFalse(symbolMap.isEmpty());
    }


}
