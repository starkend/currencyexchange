package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.dto.*;
import com.starken.currencyexchange.service.SymbolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SymbolController {

    private final static Logger log = LogManager.getLogger(SymbolController.class);

    @Autowired
    SymbolService symbolService;

    @PostMapping("/addSymbol")
    public SymbolDto addSymbol(@RequestBody SymbolDto symbolDto) {
        return symbolService.addSymbol(symbolDto);
    }

    @PostMapping("retrieveQuote")
    public QuoteDto getQuote(@RequestBody String symbolDto) {
        return symbolService.retrieveQuote(new SymbolDto(symbolDto));
    }

    @PostMapping("retrieveRate")
    public RateDto getRate(@RequestBody SymbolDto symbolDto) {
        return symbolService.retrieveRate(new SymbolDto(symbolDto.getSymbol1(), symbolDto.getSymbol2()));
    }

    @PostMapping("/convertCurrency")
    public CurrencyDto convertCurrency(@RequestBody ConvertCurrencyDto convertCurrencyDto) {
        return symbolService.convertCurrency(convertCurrencyDto);
    }

    @GetMapping("/savedSymbols")
    public List<SymbolDto> getSavedSymbols() {
        return symbolService.getSavedSymbols();
    }

    @GetMapping("/symbols")
    public List<SymbolDto> getSymbolAngularList() {
        return symbolService.getSymbols();
    }

    @GetMapping("/symbolsMap")
    public Map<String, List<String>> getSymbolsMap() {
        Map<String, List<String>> symbolMap = symbolService.getSymbolTradingPairMap();

        return symbolMap;
    }
}
