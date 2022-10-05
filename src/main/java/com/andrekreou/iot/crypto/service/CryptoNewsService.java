package com.andrekreou.iot.crypto.service;

import com.andrekreou.iot.crypto.model.CryptoNews;
import com.andrekreou.iot.crypto.repository.CryptoNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//The service layer class for business logic implementation
@org.springframework.stereotype.Service
@Transactional
public class CryptoNewsService {

    //Dependency injection to connect with Repository layer
    private final CryptoNewsRepo cryptoNewsRepo;

    @Autowired
    public CryptoNewsService(CryptoNewsRepo cryptoNewsRepo) {
        this.cryptoNewsRepo = cryptoNewsRepo;
    }

    public List<List<CryptoNews>> getData() {
        return Collections.singletonList(cryptoNewsRepo.findAll());
    }

    //Method to display all contents of database
    public List<CryptoNews> showAllRates(){
        List<CryptoNews> rates = new ArrayList<>();
        rates.addAll(cryptoNewsRepo.findAll());
        return rates;
    }
}