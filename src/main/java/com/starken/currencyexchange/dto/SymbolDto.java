package com.starken.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolDto implements Comparable<SymbolDto> {

    public SymbolDto(String symbolDto) {
        this.symbolDto = symbolDto;
    }

    public SymbolDto() {
    }

    private String symbolDto;

    public String getSymbolDto() {
        return symbolDto;
    }

    public void setSymbolDto(String symbolDto) {
        this.symbolDto = symbolDto;
    }

    public String getSymbol1() {
        return symbolDto.substring(0, 3);
    }

    public String getSymbol2() {
        return symbolDto.substring(3);
    }

    public String getSymbolPair() {
        return symbolDto;
    }

    @Override
    public String toString() {
        return "SymbolDto{" +
                "symbolDto='" + symbolDto + '\'' +
                '}';
    }

    @Override
    public int compareTo(SymbolDto o) {
        return this.getSymbolDto().compareTo(o.getSymbolDto());
    }
}
