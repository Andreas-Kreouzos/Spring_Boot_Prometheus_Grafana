package com.andrekreou.iot.bitpay.service;

import com.andrekreou.iot.bitpay.model.BitPayRates;
import com.andrekreou.iot.bitpay.repository.BitPayRatesRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//The service layer class for business logic implementation
@org.springframework.stereotype.Service
@Transactional
public class NewsService {

    //Dependency injection to connect with Repository layer
    private final BitPayRatesRepo bitPayRatesRepo;

    @Autowired
    public NewsService(BitPayRatesRepo bitPayRatesRepo) {
        this.bitPayRatesRepo = bitPayRatesRepo;
    }

    public List<List<BitPayRates>> getData() {
        return Collections.singletonList(bitPayRatesRepo.findAll());
    }

    //Method to display all contents of database
    public List<BitPayRates> showAllRates(){
        List<BitPayRates> rates = new ArrayList<>();
        rates.addAll(bitPayRatesRepo.findAll());
        return rates;
    }
}