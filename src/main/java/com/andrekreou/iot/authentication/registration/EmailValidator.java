package com.andrekreou.iot.authentication.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {

    private final Pattern USER_INPUT_PATTERN;

    @Autowired
    public EmailValidator(@Value("${string.regexp}") String regex) {
        this.USER_INPUT_PATTERN = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public boolean test(String emailAddress) {
        return USER_INPUT_PATTERN.matcher(emailAddress).matches();
    }
}