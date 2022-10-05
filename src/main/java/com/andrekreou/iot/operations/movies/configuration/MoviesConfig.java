package com.andrekreou.iot.operations.movies.configuration;

import com.andrekreou.iot.operations.movies.model.Movies;
import com.andrekreou.iot.operations.movies.repository.MoviesRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MoviesConfig {

    @Bean
    CommandLineRunner moviesCommandLineRunner(MoviesRepo moviesRepo){
        return args -> {
            String url = "https://imdb-api.com/en/API/BoxOfficeAllTime/k_2ws0dqat";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> postEntity = getResponseEntity(url, restTemplate);
            List<Movies> moviesList = new ArrayList<>();
            ObjectMapper mapper = getObjectMapper();
            scrapperJson(postEntity, moviesList, mapper);
            moviesRepo.saveAll(moviesList);
            System.out.println(moviesList);
        };
    }

    private static ResponseEntity<String> getResponseEntity(
            String url,
            RestTemplate restTemplate) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class);
    }

    private static ObjectMapper getObjectMapper() {
        return JsonMapper
                .builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).build();
    }

    private static void scrapperJson(ResponseEntity<String> postEntity,
                                     List<Movies> moviesList,
                                     ObjectMapper mapper) throws JsonProcessingException {
        JsonNode actualObj = mapper.readTree(postEntity.getBody());
        JsonNode node = actualObj.get("items");
        if (node.isArray()) {
            for (final JsonNode items : node) {
                Movies newJsonNode = mapper.treeToValue(items, Movies.class);
                moviesList.add(newJsonNode);
            }
        }
    }
}
