package com.andrekreou.iot.bitpay.controller;

import com.andrekreou.iot.bitpay.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NewsHTMLController {

    NewsService newsService;

    @Autowired
    public NewsHTMLController(NewsService newsService) {
        this.newsService = newsService;
    }

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("message", message);
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    //Method to handle the HTTP request for showing DB contents
    @GetMapping("/show-newscontents")
    public String showAllRates(HttpServletRequest request){
        request.setAttribute("rates", newsService.showAllRates());
        return "newsdbcontents";
    }
}