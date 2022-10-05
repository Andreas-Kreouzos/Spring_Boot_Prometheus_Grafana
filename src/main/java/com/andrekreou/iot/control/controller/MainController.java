package com.andrekreou.iot.control.controller;

import com.andrekreou.iot.control.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
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

    //Method to handle the HTTP request for showing DB contents of Crypto News
    @GetMapping("/show-news-contents")
    public String showAllRates(HttpServletRequest request){
        request.setAttribute("rates", mainService.showAllRates());
        return "newsdbcontents";
    }

    //Method to handle the HTTP request for showing DB contents of Movies Stats
    @GetMapping("/show-movies-contents")
    public String showAllMovies(HttpServletRequest request){
        request.setAttribute("movies", mainService.showAllMovies());
        return "moviesdbcontents";
    }
}