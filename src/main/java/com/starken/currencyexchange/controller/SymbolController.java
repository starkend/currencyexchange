package com.starken.currencyexchange.controller;

import com.starken.currencyexchange.dto.SymbolDto;
import com.starken.currencyexchange.model.Symbol;
import com.starken.currencyexchange.repository.SymbolRepository;
import com.starken.currencyexchange.service.Forex1Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

/*    @PostMapping("/addSymbol")
    public void addSymbol(@RequestBody SymbolDto symbolDto) {
        SymbolDto dto = symbolDto;
        Symbol symbol = new Symbol(dto.getSymbolPair());

        System.out.println("***********************Been called from UI for addSymbol");

        try {
            symbolRepository.save(symbol);
            //model.addAttribute("sym", dto.getSymbol1() + " => " + dto.getSymbol2());
            log.info("Symbol successfully saved: " + dto.getSymbolPair());
            //return "success";
        } catch (DataAccessException dae) {
            //model.addAttribute("errorText", "Sorry mate, something's been bottled, Data Access");
            log.error(dae.getMessage());
            //return "error";
        }
    }*/

    @PostMapping("/addSymbol")
    public SymbolDto addSymbol(@RequestBody SymbolDto symbolDto) {
        Symbol savedSymbol = symbolRepository.save(new Symbol(symbolDto.getSymbolPair()));
        if (savedSymbol != null) {
            return symbolDto;
        } else {
            return new SymbolDto();
        }

    }

    @GetMapping("/savedSymbols")
    public List<SymbolDto> getSymbolList() {

        Iterable<Symbol> symbolIterable = symbolRepository.findAll();

//        List<SymbolDto> savedSymbolList = new ArrayList<>();
//
//        for (Symbol symbol : symbolIterable) {
//            savedSymbolList.add(new SymbolDto(symbol.getSymbolPair()));
//        }

        List<SymbolDto> savedSymbolDtoList = new ArrayList<>();

        savedSymbolDtoList = StreamSupport.stream(symbolIterable.spliterator(), false)
                .map(s -> new SymbolDto(s.getSymbolPair()))
                .collect(Collectors.toList());

//        savedSymbolDtoList = IteratorUtils.toList(symbolIterable.iterator());


        return savedSymbolDtoList;
    }

    @GetMapping("/symbolsAngularList")
    public List<SymbolDto> getSymbolAngularList() {
        List<SymbolDto> symbolDtos = forex1Service.getSymbols();

        return symbolDtos;
    }
}
