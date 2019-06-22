package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.service.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@PropertySource("classpath:application.properties")
@EnableElasticsearchRepositories(basePackages = { "com.starken.currencyexchange.repository"})
public class CurrencyexchangeConfiguration implements WebMvcConfigurer {

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SymbolService symbolService() {
        return new SymbolServiceImpl();
    }

    @Bean
    public EcbService ecbService() {
        return new EcbServiceImpl();
    }

    @Bean
    public CoinbaseService coinbaseService() {
        return new CoinbaseServiceImpl();
    }

    @Bean
    public CurrencyService currencyService() {
        return new CurrencyServiceImpl();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Value("${elasticsearch.host:localhost}")
    public String host;
    @Value("${elasticsearch.port:9300}")
    public int port;
    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }

    @Bean
    public Client client(){
        TransportClient client = null;
        try{

            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }
}
