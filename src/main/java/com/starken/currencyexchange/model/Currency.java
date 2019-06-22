package com.starken.currencyexchange.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Objects;

@Document(indexName = "currencyindex", type = "currency")
public class Currency {

    @Id
    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Currency() {}

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) &&
                Objects.equals(name, currency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
