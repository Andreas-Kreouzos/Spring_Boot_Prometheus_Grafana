package com.andrekreou.iot.authentication.user;

import com.andrekreou.iot.authentication.registration.token.ConfirmationToken;
import com.andrekreou.iot.authentication.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(
                                String.format(USER_NOT_FOUND_MSG, email))));
    }

    public String signUpUser(ApplicationUser applicationUser, ConfirmationToken confirmationToken) {
        boolean mailExists = doesMailExists(applicationUser);
        checkEmail(confirmationToken, mailExists);
        String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        userRepository.save(applicationUser);
        String token = UUID.randomUUID().toString();
        confirmationToken = getConfirmationToken(applicationUser, token);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    private boolean doesMailExists(ApplicationUser applicationUser) {
        return userRepository.findByEmail(applicationUser.getEmail()).isPresent();
    }

    private static void checkEmail(ConfirmationToken confirmationToken, boolean mailExists) {
        if (mailExists && (confirmationToken.getConfirmedAt() != null)) {
            throw new IllegalStateException("email already taken");
        }
    }

    private static ConfirmationToken getConfirmationToken(
            ApplicationUser applicationUser,
            String token) {
        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                applicationUser
        );
    }

    public int enableApplicationUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
