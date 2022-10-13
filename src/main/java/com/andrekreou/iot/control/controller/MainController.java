package com.andrekreou.iot.control.controller;

import com.andrekreou.iot.control.service.MainService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    private final MainService mainService;
    private final Counter hitCounter;

    @Autowired
    public MainController(MainService mainService, MeterRegistry meterRegistry) {
        this.mainService = mainService;
        this.hitCounter = Counter.builder("hit_counter")
                .description("Number of Hits")
                .register(meterRegistry);
    }

    @GetMapping("/")
    public String main(Model model, Principal principal){
        String name = principal.getName();
        model.addAttribute("name",name);
        return "welcome";
    }

    @GetMapping("/login-error")
    public void getLoginErrorView(){
        throw new UsernameNotFoundException("User with this email doesn't exist");
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/show-news-contents")
    public String showAllRates(HttpServletRequest request){
        request.setAttribute("rates", mainService.showAllRates());
        hitCounter.increment();
        return "news-db-contents";
    }

    @Timed(value = "show-all-movies.time", description = "Time taken to return Movies")
    @GetMapping("/show-movies-contents")
    public String showAllMovies(HttpServletRequest request){
        request.setAttribute("movies", mainService.showAllMovies());
        return "movies-db-contents";
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    @GetMapping("/registration-complete")
    public String showRegistrationCompleteForm(){
        return "registration-complete";
    }

    @GetMapping("/verification-complete")
    public String showVerificationCompleteForm(){
        return "verification-complete";
    }
}