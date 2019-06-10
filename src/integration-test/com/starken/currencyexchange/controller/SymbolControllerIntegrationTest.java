package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.service.CoinbaseService;
import com.starken.currencyexchange.service.EcbService;
import com.starken.currencyexchange.service.SymbolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SymbolController.class)
public class SymbolControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SymbolService symbolService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private EcbService ecbService;

    @MockBean
    private CoinbaseService coinbaseService;


    @Test
    public void givenSymbols_whenGetSymbols_thenReturnSymbolJsonArray() throws Exception {
        SymbolDto usdAud = new SymbolDto("USDAUD");

        List<SymbolDto> symbolList = Arrays.asList(usdAud);

        given(symbolService.getSymbols()).willReturn(symbolList);

        mvc.perform(get("/symbols")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].symbolPair", is(usdAud.getSymbolPair())));
    }

}
