package com.starken.currencyexchange.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.com.starken.currencyexchange.forex1.dto.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Forex1ServiceImpl implements Forex1Service {

    RestTemplate restTemplate;
    ObjectMapper objectMapper;

    private final String forex1url = "https://forex.1forge.com/1.0.3/symbols";
    private final String api_key = "HgHvWV9ajNU3KYs83yrOaIo0ko4rT7yx";

    public Forex1ServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Symbol> getSymbols() {
        StringBuilder sb = new StringBuilder();

        sb.append(forex1url);
        sb.append("?api_key=");
        sb.append(api_key);

        List<String> jsonSymbols = CollectionUtils.arrayToList(restTemplate.getForObject(sb.toString(), String[].class));
//        String[] jsonSymbols = restTemplate.getForObject(sb.toString(), String[].class);


        jsonSymbols.forEach(System.out::println);
//        List<String> list = objectMapper.readValue(jsonSymbols, TypeFactory.defaultInstance().constructCollectionType(List.class, String[].class));


        return null;
    }
}
