package com.andrekreou.iot.bitpay.configuration;

import com.andrekreou.iot.bitpay.model.BitPayRates;
import com.andrekreou.iot.bitpay.repository.BitPayRatesRepo;
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

//The configuration class to fetch data from url and execute the insertion
//of the data into the PostgreSQL database
@Configuration
public class BitPayRatesConfig {

    @Bean
    CommandLineRunner commandLineRunner(BitPayRatesRepo bitPayRatesRepo){
        return args -> {
            String url = "https://investing-cryptocurrency-markets.p.rapidapi.com/coins/get-news?pair_ID=1057391";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-RapidAPI-Key","605a619252msh709d3d6fa6fbdcdp155ef4jsn6cd36a1a4195");
            headers.set("X-RapidAPI-Host","investing-cryptocurrency-markets.p.rapidapi.com");

            HttpEntity<Object> request = new HttpEntity<>(headers);

            ResponseEntity<String> postEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class);

            List<BitPayRates> bitPayRatesList = new ArrayList<>();
            ObjectMapper mapper = JsonMapper
                    .builder()
                    .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).build();

            JsonNode actualObj = mapper.readTree(postEntity.getBody());
            JsonNode node = actualObj.get("data");
            if (node.isArray()) {
                for (final JsonNode objNode : node) {
                    JsonNode screenDataNode = objNode.get("screen_data");
                    JsonNode newsNode = screenDataNode.get("news");
                    for(JsonNode news : newsNode){
                        BitPayRates newJsonNode = mapper.treeToValue(news, BitPayRates.class);
                        bitPayRatesList.add(newJsonNode);
                    }
                }
            }
            bitPayRatesRepo.saveAll(bitPayRatesList);
            System.out.println(bitPayRatesList);
        };
    }
}
