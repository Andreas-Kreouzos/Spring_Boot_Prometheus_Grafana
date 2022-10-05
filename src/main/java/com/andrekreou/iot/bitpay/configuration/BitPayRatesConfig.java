package com.andrekreou.iot.bitpay.configuration;

import com.andrekreou.iot.bitpay.model.BitPayRates;
import com.andrekreou.iot.bitpay.repository.BitPayRatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//The configuration class to fetch data from url and execute the insertion
//of the data into the PostgreSQL database
@Configuration
@EnableScheduling
public class BitPayRatesConfig {

    @Autowired
    private BitPayRatesRepo bitPayRatesRepo;

    @Scheduled(fixedRate = 20000)
    public void add2DB(){

        String url = "https://bitpay.com/api/rates";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<BitPayRates>> postEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        List<BitPayRates> results = postEntity.getBody();
        bitPayRatesRepo.saveAll(results);
        System.out.println(results);
    }
}
