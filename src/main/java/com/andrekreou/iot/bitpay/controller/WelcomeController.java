package com.andrekreou.iot.bitpay.controller;

import com.andrekreou.iot.bitpay.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {

    Service service;

    @Autowired
    public WelcomeController(Service service) {
        this.service = service;
    }

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("message", message);

        return "welcome";
    }

    //Method to handle the HTTP request for showing DB contents
    @GetMapping("/show-contents")
    public String showAllRates(HttpServletRequest request){
        request.setAttribute("rates", service.showAllRates());
        return "databasecontents";
    }
}
