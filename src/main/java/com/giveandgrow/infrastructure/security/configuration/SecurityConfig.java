package com.giveandgrow.infrastructure.security.configuration;

import com.giveandgrow.infrastructure.security.filter.JwtAuthenticationFilter;
import com.giveandgrow.infrastructure.security.jwt.JwtService;
import com.giveandgrow.infrastructure.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1) Usa el cors(Customizer) en lugar de cors() sin args
                .cors(Customizer.withDefaults())
                // 2) Deshabilita CSRF para API REST
                .csrf(AbstractHttpConfigurer::disable)
                // 3) Stateless: no guarda sesión
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 4) Configura accesos públicos y protegidos
                .authorizeHttpRequests(auth -> auth
                        // permitir POST/GET a /api/v1/organizations/**
                        .requestMatchers(HttpMethod.POST, "/api/v1/events").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/v1/events/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/events").permitAll()

                        //register and dummy org
                        .requestMatchers(HttpMethod.POST, "/api/v1/organizations/").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/v1/organizations/dummy").permitAll()
                        
                        // login(user and org) y register of user
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/r").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/dummy").permitAll()

                        // login y registro de organizaciones

                        // el resto requiere token válido
                        .anyRequest().authenticated()
                )
                // 5) Añade tu filtro JWT antes de la autenticación por formulario
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtService, customUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig
    ) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
