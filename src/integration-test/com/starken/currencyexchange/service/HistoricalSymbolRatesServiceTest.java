package com.starken.currencyexchange.service;

import com.starken.currencyexchange.CurrencyexchangeApplication;
import com.starken.currencyexchange.dto.HistoricalSymbolRatesDto;
import com.starken.currencyexchange.model.HistoricalRates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyexchangeApplication.class)
public class HistoricalSymbolRatesServiceTest {

    @Autowired
    HistoricalSymbolRatesService historicalSymbolRatesService;

    @Autowired
    EcbService ecbService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

//    @Test
//    public void whenHistoricalRatesByBase_thenReturnPopulatedHistoricalRatesSymbolDto() {
//        HistoricalSymbolRatesDto historicalSymbolRatesDto;
//
//        historicalSymbolRatesDto = ecbService.getHistoricalRatesByBase("USD");
//
//        assertNotNull(historicalSymbolRatesDto);
//        assertFalse(historicalSymbolRatesDto.getRates().isEmpty());
//
//        Date date = new Date();
//
//        HistoricalRates newRates = new HistoricalRates(date.toString(),
//                historicalSymbolRatesDto.getBase(),
//                historicalSymbolRatesDto.getRates());
//
//        HistoricalRates newDto = historicalSymbolRatesService.save(newRates);
//
//        assertNotNull(newDto);
//
//    }

    @Test
    public void whenFindAll_thenReturnAll() {
        List<HistoricalRates> historicalRates;

        historicalRates = historicalSymbolRatesService.findAll();

        assertFalse(historicalRates.isEmpty());
    }

    @Test
    public void whenFindByBase_thenReturnAllForBase() {
        List<HistoricalRates> historicalRates;

        historicalRates = historicalSymbolRatesService.findByBase("USD");

        assertFalse(historicalRates.isEmpty());
    }

}
