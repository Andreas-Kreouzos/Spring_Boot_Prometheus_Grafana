package com.andrekreou.iot.crypto.controller;

import com.andrekreou.iot.crypto.model.CryptoNews;
import com.andrekreou.iot.crypto.service.CryptoNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//The controller for the project, which handles HTTP requests
@RestController
public class Controller {

    //Dependency injection to connect with CryptoNewsService layer
    private final CryptoNewsService cryptoNewsService;

    @Autowired
    public Controller(CryptoNewsService cryptoNewsService) {
        this.cryptoNewsService = cryptoNewsService;
    }

    @GetMapping(path = "/bitpay")
    public List<List<CryptoNews>> getData(){
        return cryptoNewsService.getData();
    }
}
