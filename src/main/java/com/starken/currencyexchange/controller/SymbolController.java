package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.service.SymbolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/savedSymbols")
    public List<SymbolDto> getSavedSymbols() {
        return symbolService.getSymbols();
    }

    @GetMapping("/symbolsAngularList")
    public List<SymbolDto> getSymbolAngularList() {
        return symbolService.getSymbols();
    }
}
