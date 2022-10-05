package com.andrekreou.iot.bitpay.controller;

import com.andrekreou.iot.bitpay.service.Service;
import com.andrekreou.iot.bitpay.model.BitPayRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//The controller for the project, which handles HTTP requests
@RestController
public class Controller {

    //Dependency injection to connect with Service layer
    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(path = "/bitpay")
    public List<List<BitPayRates>> getData(){
        return service.getData();
    }
}
