package com.andrekreou.iot.operations.crypto.configuration;

import com.andrekreou.iot.operations.crypto.model.CryptoNews;
import com.andrekreou.iot.operations.crypto.repository.CryptoNewsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class CryptoNewsConfig {

    @Bean
    CommandLineRunner newsCommandLineRunner(CryptoNewsRepo cryptoNewsRepo){
        return args -> {
            String url = "https://investing-cryptocurrency-markets.p.rapidapi.com/coins/get-news?pair_ID=1057391";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = getHttpHeaders();
            HttpEntity<Object> request = new HttpEntity<>(headers);
            ResponseEntity<String> postEntity = getResponseEntity(url, restTemplate, request);
            List<CryptoNews> cryptoNewsList = new ArrayList<>();
            ObjectMapper mapper = getObjectMapper();
            scrapperJson(postEntity, cryptoNewsList, mapper);
            cryptoNewsRepo.saveAll(cryptoNewsList);
            System.out.println(cryptoNewsList);
        };
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-RapidAPI-Key","605a619252msh709d3d6fa6fbdcdp155ef4jsn6cd36a1a4195");
        headers.set("X-RapidAPI-Host","investing-cryptocurrency-markets.p.rapidapi.com");
        return headers;
    }

    private static ResponseEntity<String> getResponseEntity(
            String url,
            RestTemplate restTemplate,
            HttpEntity<Object> request) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class);
    }

    private static ObjectMapper getObjectMapper() {
        return JsonMapper
                .builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).build();
    }

    private static void scrapperJson(ResponseEntity<String> postEntity,
                                     List<CryptoNews> cryptoNewsList,
                                     ObjectMapper mapper) throws JsonProcessingException {
        JsonNode actualObj = mapper.readTree(postEntity.getBody());
        JsonNode node = actualObj.get("data");
        if (node.isArray()) {
            for (final JsonNode objNode : node) {
                JsonNode screenDataNode = objNode.get("screen_data");
                JsonNode newsNode = screenDataNode.get("news");
                for(JsonNode news : newsNode){
                    CryptoNews newJsonNode = mapper.treeToValue(news, CryptoNews.class);
                    cryptoNewsList.add(newJsonNode);
                }
            }
        }
    }
}
