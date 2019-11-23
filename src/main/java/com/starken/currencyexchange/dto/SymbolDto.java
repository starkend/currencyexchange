package com.starken.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Comparator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolDto implements Comparable<SymbolDto> {

    private Long id;
    private String symbol1;
    private String symbol2;


    public SymbolDto(String symbolString) {
        if (symbolString.length() == 6) {
            this.symbol1 = symbolString.substring(0,3);
            this.symbol2 = symbolString.substring(3);
        } else {
            this.symbol1 = "";
            this.symbol2 = "";
        }
    }

    public SymbolDto(String symbolString, Long id) {
        if (symbolString.length() == 6) {
            this.symbol1 = symbolString.substring(0,3);
            this.symbol2 = symbolString.substring(3);
        } else {
            this.symbol1 = "";
            this.symbol2 = "";
        }
        this.id = id;
    }

    public SymbolDto(String symbol1, String symbol2) {
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
    }

    public SymbolDto() {
    }

    public void setSymbol1(String symbol1) {
        this.symbol1 = symbol1;
    }

    public void setSymbol2(String symbol2) {
        this.symbol2 = symbol2;
    }

    public String getSymbolPair() {
        return symbol1 + symbol2;
    }

    public String getSymbol1() {
        return symbol1;
    }

    public String getSymbol2() {
        return symbol2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int compareTo(SymbolDto o) {
        return Comparator.comparing(SymbolDto::getSymbol1)
                .thenComparing(SymbolDto::getSymbol2)
                .compare(this,o);
    }

    @Override
    public String toString() {
        return "SymbolDto{" +
                "id=" + id +
                ", symbol1='" + symbol1 + '\'' +
                ", symbol2='" + symbol2 + '\'' +
                '}';
    }
}
