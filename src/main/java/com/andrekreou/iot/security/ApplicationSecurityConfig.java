package com.andrekreou.iot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//WebSecurityConfigurerAdapter has been deprecated

@Configuration
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                .loginPage("/login").permitAll();

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails andreasKreouzos = User.builder()
                .username("andreaskreouzos")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(andreasKreouzos);
    }
}
