package com.starken.currencyexchange.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.forex1.dto.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class Forex1ServiceImpl implements Forex1Service {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.forex1.url}")
    private String forex1url;

    @Value("${service.forex1.api_key}")
    private String api_key;

    @Override
    public List<Symbol> getSymbols() {
        StringBuilder sb = new StringBuilder();

        sb.append(forex1url);
        sb.append("?api_key=");
        sb.append(api_key);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(sb.toString(), String.class);

        List<Symbol> symbolList = null;
        try {
            symbolList = objectMapper.readValue(forEntity.getBody(), new TypeReference<List<Symbol>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return symbolList;
    }
}
