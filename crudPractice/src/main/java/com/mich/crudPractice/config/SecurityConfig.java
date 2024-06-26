package com.mich.crudPractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    public SecurityConfig() {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin").password(
                this.passwordEncoder().encode("admin123")
        ).roles(new String[]{"ADMIN"}).build();
        UserDetails student = User.withUsername("student").password(
                this.passwordEncoder().encode("osmich05"))
                .roles(new String[]{"RECE"}).build();

        return new InMemoryUserDetailsManager(new UserDetails[]{admin,student});
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
        httpSecurity.authorizeHttpRequests((request) -> {
            request.requestMatchers("/", "/index").permitAll();
            request.anyRequest().authenticated();
        });

        httpSecurity.formLogin((login) -> {
            login.loginPage("/login").permitAll();
        });

        httpSecurity.logout((logout) -> {
            logout.permitAll();
        });

        return httpSecurity.build();
    }

}
