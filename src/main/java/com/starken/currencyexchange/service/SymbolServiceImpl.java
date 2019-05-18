package com.starken.currencyexchange.service;

import com.starken.currencyexchange.dto.*;
import com.starken.currencyexchange.model.Symbol;
import com.starken.currencyexchange.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SymbolServiceImpl implements SymbolService {

    @Autowired
    Forex1Service forex1Service;

    @Autowired
    EcbService ecbService;

    @Autowired
    SymbolRepository symbolRepository;

    public SymbolServiceImpl() { }

    @Override
    public List<SymbolDto> getSymbols() {
        return ecbService.getSymbolDtoList();
    }

    @Override
    public Map<String, List<String>> getSymbolTradingPairMap() {
        return ecbService.getSymbolsMap();
    }

    @Override
    public QuoteDto retrieveQuote(SymbolDto symbolDto) {
        return forex1Service.retrieveQuote(symbolDto);
    }

    @Override
    public RateDto retrieveRate(SymbolDto symbolDto) {
        return ecbService.getSingleLatestSymbolRateByBase(symbolDto);
    }

    @Override
    public CurrencyDto convertCurrency(ConvertCurrencyDto convertCurrencyDto) {
        return forex1Service.convertCurrency(convertCurrencyDto);
    }

    @Override
    public List<SymbolDto> getSavedSymbols() {
        Iterable<Symbol> symbolIterable = symbolRepository.findAll();
        List<SymbolDto> savedSymbolDtoList;

        savedSymbolDtoList = StreamSupport.stream(symbolIterable.spliterator(), false)
                .map(s -> new SymbolDto(s.getSymbolPair()))
                .collect(Collectors.toList());

        return savedSymbolDtoList;
    }

    @Override
    public boolean getMarketStatus() {
        return forex1Service.getMarketStatus();
    }

    @Override
    public SymbolDto addSymbol(SymbolDto symbolDto) {
        Symbol savedSymbol = symbolRepository.save(new Symbol(symbolDto.getSymbolPair()));

        if (savedSymbol != null) {
            return symbolDto;
        } else {
            return null;
        }
    }

    @Override
    public Symbol findBySymbolPair(String symbolPair) {
        return symbolRepository.findBySymbolPair(symbolPair);
    }

    @Override
    public List<String> getSymbolsList() { return ecbService.getSymbolsList();   }
}
