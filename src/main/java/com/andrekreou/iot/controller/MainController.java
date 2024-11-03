package com.andrekreou.iot.controller;

import com.andrekreou.iot.service.MainService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

@Tag(
        name = "Main Controller",
        description = "Main Controller Exposes REST APIs"
)
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

    @Operation(
            summary = "Main Screen REST API",
            description = "Main Screen REST API is used to display the main screen after successful login"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/")
    public String main(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping("/login-error")
    public void getLoginErrorView() {
        throw new UsernameNotFoundException("User with this email doesn't exist");
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @Timed(value = "show-all-rates.time", description = "Time taken to return Rates")
    @GetMapping("/show-news-contents")
    public String showAllRates(HttpServletRequest request) {
        mainService.persistCrypto();
        request.setAttribute("rates", mainService.showAllRates());
        hitCounter.increment();
        return "news-db-contents";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/registration-complete")
    public String showRegistrationCompleteForm() {
        return "registration-complete";
    }

    @GetMapping("/verification-complete")
    public String showVerificationCompleteForm() {
        return "verification-complete";
    }
}