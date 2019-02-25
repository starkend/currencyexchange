package com.starken.currencyexchange.model;

import javax.persistence.*;

@Entity
@Table(name="symbol")
@Access(value=AccessType.FIELD)
public class Symbol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    public Symbol() {}

    public Symbol(String symbolPair) {
        this.symbolPair = symbolPair;
    }

    @Column(name="symbol_pair")
    private String symbolPair;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbolPair() {
        return symbolPair;
    }

    public void setSymbolPair(String symbolPair) {
        this.symbolPair = symbolPair;
    }

    public String getSymbol1() {
        return symbolPair.substring(0,3);
    }

    public String getSymbol2() { return symbolPair.substring(3);
    }

}
