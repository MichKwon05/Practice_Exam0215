package mx.edu.utez.extradelextra;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.extradelextra.model.Login;
import mx.edu.utez.extradelextra.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private LoginService loginService;

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMINISTRATOR")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("capturista")
                .password("123")
                .roles("TRANSCRIBER")
                .build();
        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("cliente")
                .password("123")
                .roles("CLIENT")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/", "/assets/**", "/bitacora").permitAll()
                .requestMatchers("/capturistas").hasAnyRole("ADMINISTRATOR")
                .requestMatchers("/clientes").hasAnyRole("ADMINISTRATOR", "TRANSCRIBER")
                .requestMatchers("/pedidos").hasAnyRole("ADMINISTRATOR", "TRANSCRIBER", "CLIENT")
                .requestMatchers("/productos").hasAnyRole("CLIENT", "ANONYMOUS")
                .requestMatchers("/registro").hasAnyRole("ANONYMOUS")
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        Login login = new Login(authentication.getName(), request.getRemoteAddr());
                        loginService.save(login);
                        response.sendRedirect("/");
                    }
                });
        return http.build();
    }
}
