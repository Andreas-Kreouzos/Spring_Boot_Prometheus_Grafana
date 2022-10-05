package com.andrekreou.iot.auth;

import com.andrekreou.iot.registration.RegistrationRequest;
import com.andrekreou.iot.registration.RegistrationService;
import com.andrekreou.iot.registration.token.ConfirmationToken;
import com.andrekreou.iot.registration.token.ConfirmationTokenRepository;
import com.andrekreou.iot.registration.token.ConfirmationTokenService;
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

    public String signUpUser(ApplicationUser applicationUser){
        boolean mailExists = isMailExists(applicationUser);

        if (mailExists) {
           throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());

        applicationUser.setPassword(encodedPassword);

        userRepository.save(applicationUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                applicationUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    private boolean isMailExists(ApplicationUser applicationUser) {
        return userRepository.findByEmail(applicationUser.getEmail()).isPresent();
    }

    public int enableApplicationUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
