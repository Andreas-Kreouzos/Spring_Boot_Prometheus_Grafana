package com.andrekreou.iot.security;

import com.andrekreou.iot.auth.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.andrekreou.iot.security.ApplicationUserRole.ADMIN;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class ApplicationSecurityConfig {

    private final ApplicationUserService applicationUserService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v*/registration/**").permitAll()
                    .antMatchers("/show-newscontents").hasRole(ADMIN.name())
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/",true)
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","Idea-2e8e7cee")
                    .logoutSuccessUrl("/login");

        return http.build();
    }

/*    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

/*    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails andreasKreouzos = User.builder()
                .username("andreaskreouzos")
                .password(passwordEncoder.encode("password123"))
                .roles(ADMIN.name())
                //.authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails chrisPapadopoulos = User.builder()
                .username("chris")
                .password(passwordEncoder.encode("password"))
                .roles(USER.name())
                //.authorities(USER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(andreasKreouzos,chrisPapadopoulos);
    }*/
}
