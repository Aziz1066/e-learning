package com.fst.elearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import com.fst.elearning.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final CustomSuccessHandler successHandler;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          CustomSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // PUBLIC pages
                .requestMatchers(
                    "/", "/home", "/login", "/register",
                    "/css/**", "/js/**", "/images/**",
                    "/uploads/**"   // still OK
                ).permitAll()

                // ROLE-BASED routes
                .requestMatchers("/formateur/**").hasRole("FORMATEUR")
                .requestMatchers("/apprenant/**").hasRole("APPRENANT")
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // authenticated only
                .requestMatchers("/courses").authenticated()

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureUrl("/login?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            

            .userDetailsService(userDetailsService);
            

        return http.build();
    }

    // 🔥 IMPORTANT FIX (THIS IS WHAT YOU WERE MISSING)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/uploads/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}