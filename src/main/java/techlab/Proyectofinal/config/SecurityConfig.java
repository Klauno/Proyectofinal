package techlab.Proyectofinal.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Cadena de seguridad para la consola H2 con alta prioridad
    @Bean
    @Order(1) // Se ejecuta primero para la consola H2
    public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(PathRequest.toH2Console()) // Aplica solo para /h2-console/**
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permitir todo sin autenticación
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para H2 Console
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // Permitir iframe same origin
        return http.build();
    }

    // Cadena de seguridad para el resto de la aplicación
    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {}) // Habilita CORS con configuración por defecto o global
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/producto/**", "/pedido/**", "/categoria/**", "/usuario/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}
