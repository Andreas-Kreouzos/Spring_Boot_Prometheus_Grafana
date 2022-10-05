package com.andrekreou.iot.registration;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class RegistrationViewController {

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    @GetMapping("/registration-complete")
    public String showRegistrationCompleteForm(){
        return "registration-complete";
    }
}
