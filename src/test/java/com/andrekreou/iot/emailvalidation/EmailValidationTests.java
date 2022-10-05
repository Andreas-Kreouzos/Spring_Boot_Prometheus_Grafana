package com.andrekreou.iot.emailvalidation;

import com.andrekreou.iot.authentication.registration.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailValidationTests {

    @Test
    @DisplayName("Check the input email validity based on the RFC822 pattern")
    public void testUsingRFC822Regex() {
        String emailAddress = "username@domain.com";
        assertTrue(EmailValidator.patternMatches(emailAddress));
    }

}
