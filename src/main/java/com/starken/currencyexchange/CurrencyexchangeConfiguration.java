package com.starken.currencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starken.currencyexchange.service.*;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@EnableElasticsearchRepositories(basePackages = {"com.starken.currencyexchange.repository"})
public class CurrencyexchangeConfiguration extends AbstractElasticsearchConfiguration implements WebMvcConfigurer {

    @Value("${elasticsearch.host:localhost}")
    public String host;
    @Value("${elasticsearch.port:9300}")
    public int port;
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

    @Bean
    public HistoricalSymbolRatesService historicalSymbolRatesService() {
        return new HistoricalSymbolRatesServiceImpl();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

//    @Bean
//    public RestHighLevelClient client() {
//        RestHighLevelClient client = null;
//        client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost(host, port))
//        );
////                    Settings.EMPTY)
////                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
//        return client;
//    }

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.localhost()).rest();
    }
}
