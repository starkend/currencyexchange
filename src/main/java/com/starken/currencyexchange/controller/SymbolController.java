package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;
import com.starken.currencyexchange.repository.SymbolRepository;
import com.starken.currencyexchange.service.Forex1Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SymbolController {

    private final static Logger log = LogManager.getLogger(SymbolController.class);

    @Autowired
    Forex1Service forex1Service;

    @Autowired
    SymbolRepository symbolRepository;

    @GetMapping("/symbols")
    public String getSymbols(Model model) {
        List<SymbolDto> symbolDtos = forex1Service.getSymbols();
        model.addAttribute("symbolsTitle", "Currently Trading Symbol List");
        model.addAttribute("symbols", symbolDtos);

        log.info("Retrieving symbols");
        return "symbols";
    }

    @PostMapping("/symbols")
    public String addSymbol(@ModelAttribute SymbolDto symbolDto, Model model) {
        SymbolDto dto = symbolDto;
        Symbol symbol = new Symbol(dto.getSymbolPair());

        try {
            symbolRepository.save(symbol);
            model.addAttribute("sym", dto.getSymbol1() + " => " + dto.getSymbol2());
            log.info("Symbol successfully saved: " + dto.getSymbolPair());
            return "success";
        } catch (DataAccessException dae) {
            model.addAttribute("errorText", "Sorry mate, something's been bottled, Data Access");
            log.error(dae.getMessage());
            return "error";
        }
    }

    @GetMapping("/symbolList")
    public String getSymbolList(Model model) {
        log.info("********************************************************** Call from UI to getSymbolsList method ***********************");
        model.addAttribute("symbolListTitle", "Saved Symbols");
        model.addAttribute("symbols", symbolRepository.findAll());

        return "symbolList";
    }

    @GetMapping("/symbolsAngularList")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<SymbolDto> getSymbolAngularList() {
        List<SymbolDto> symbolDtos = forex1Service.getSymbols();

        return symbolDtos;
    }
}
