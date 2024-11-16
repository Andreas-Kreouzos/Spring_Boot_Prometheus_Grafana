package com.andrekreou.iot.client;

import com.andrekreou.iot.entity.CryptoNews;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class CryptoClient {

    private final RestClient restClient;
    private final ObjectMapper mapper;

    public CryptoClient(RestClient.Builder restClientBuilder, ObjectMapper mapper) {
        this.mapper = mapper;
        this.restClient = restClientBuilder.baseUrl("https://investing-cryptocurrency-markets.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Key", "605a619252msh709d3d6fa6fbdcdp155ef4jsn6cd36a1a4195")
                .defaultHeader("X-RapidAPI-Host", "investing-cryptocurrency-markets.p.rapidapi.com")
                .build();
    }

    public List<CryptoNews> fetchCryptoNews() {
        String response = restClient.get()
                .uri("/coins/get-news?pair_ID=1057391")
                .retrieve()
                .body(String.class);

        return parseNews(response);
    }

    private List<CryptoNews> parseNews(String responseBody) {
        List<CryptoNews> cryptoNewsList;
        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode newsArray = root.path("data").get(0).path("screen_data").path("news");

            cryptoNewsList = mapper.convertValue(newsArray, mapper.getTypeFactory().constructCollectionType(List.class, CryptoNews.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing CryptoNews data", e);
        }
        return cryptoNewsList;
    }
}