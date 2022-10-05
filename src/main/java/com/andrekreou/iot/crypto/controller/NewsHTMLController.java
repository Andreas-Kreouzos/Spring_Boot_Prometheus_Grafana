package com.andrekreou.iot.crypto.controller;

import com.andrekreou.iot.crypto.service.CryptoNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class NewsHTMLController {

    CryptoNewsService cryptoNewsService;

    @Autowired
    public NewsHTMLController(CryptoNewsService cryptoNewsService) {
        this.cryptoNewsService = cryptoNewsService;
    }

    @GetMapping("/")
    public String main(Model model, Principal principal){
        String name = principal.getName();
        model.addAttribute("name",name);
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    //Method to handle the HTTP request for showing DB contents
    @GetMapping("/show-news-contents")
    public String showAllRates(HttpServletRequest request){
        request.setAttribute("rates", cryptoNewsService.showAllRates());
        return "newsdbcontents";
    }
}