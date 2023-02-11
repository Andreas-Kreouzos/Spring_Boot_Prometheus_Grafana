package com.andrekreou.iot.emailvalidation;

import com.andrekreou.iot.authentication.registration.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = EmailValidator.class)
@TestPropertySource("classpath:application.properties")
public class EmailValidationTests {

    @Autowired
    private EmailValidator validator;

    @Value("${string.regex}")
    private String regex;

    @Test
    @DisplayName("Check a valid email address based on the RFC822 pattern")
    public void testValidEMailAddressUsingRFC822Regex() {
        String emailAddress = "andreas.kreouzos@hotmail.com";
        assertTrue(validator.test(emailAddress));
    }

    @Test
    @DisplayName("Check an invalid email address based on the RFC822 pattern")
    public void testInvalidEMailAddressUsingRFC822Regex() {
        String emailAddress = "andreas.kreouzoshotmail.com";
        assertFalse(validator.test(emailAddress));
    }
}
