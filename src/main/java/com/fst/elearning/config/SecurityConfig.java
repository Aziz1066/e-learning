package com.fst.elearning.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fst.elearning.service.CustomUserDetailsService;
import com.fst.elearning.config.CustomSuccessHandler;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final CustomSuccessHandler successHandler;

    // Constructor injection
    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          CustomSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ⚠️ CSRF disabled (OK for dev, enable later for production)
            .csrf(csrf -> csrf.disable())

            // 🔐 Authorization rules
            .authorizeHttpRequests(auth -> auth

                // Public pages
                .requestMatchers(
                    "/", "/home", "/login", "/register",
                    "/css/**", "/js/**", "/images/**"
                ).permitAll()

                // Role-based access
                .requestMatchers("/formateur/**").hasRole("FORMATEUR")
                .requestMatchers("/apprenant/**").hasRole("APPRENANT")
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // Everything else requires login
                .anyRequest().authenticated()
            )

            // 🔑 LOGIN CONFIG
            .formLogin(form -> form
                .loginPage("/login")               // custom login page
                .loginProcessingUrl("/login")      // form action
                .successHandler(successHandler)    // 🔥 ROLE REDIRECTION HERE
                .failureUrl("/login?error=true")
                .permitAll()
            )

            // 🚪 LOGOUT CONFIG
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )

            // 🧠 USER DETAILS SERVICE
            .userDetailsService(userDetailsService);

        return http.build();
    }

    // 🔐 Password encoder (VERY IMPORTANT)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}