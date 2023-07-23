package com.andrekreou.iot.authentication;

import com.andrekreou.iot.authentication.registration.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidationTests {

    private EmailValidator validator;

    @BeforeEach
    public void setup() {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9\\+]+(\\.[A-Za-z0-9\\+]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        validator = new EmailValidator(emailRegex);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "test.test@example.com", "test+test@example.com"})
    @DisplayName("Check valid email addresses based on specific pattern")
    public void testValidEMailAddressUsingRegex(String emailAddress) {
        assertTrue(validator.test(emailAddress));
    }

    @ParameterizedTest
    @ValueSource(strings = {"testexample.com", "test.test.@example.com", ".test+test@example.com"})
    @DisplayName("Check invalid email addresses based on specific pattern")
    public void testInvalidEMailAddressUsingRegex(String emailAddress) {
        assertFalse(validator.test(emailAddress));
    }
}