package com.andrekreou.iot.authentication.registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationViewController {
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
