package com.backend.contigo_fiscal.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .cors(Customizer.withDefaults()) 
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            // REGLA 1: Permitir OPTIONS para que el navegador no bloquee el "pre-flight"
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            
            // REGLA 2: Rutas específicas para el Chatbot (Sin contraseña)
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/catalogs/**").permitAll()
            .requestMatchers("/api/requests/**").permitAll()
            .requestMatchers("/", "/actuator/health").permitAll()
            
            // REGLA 3: Todo lo demás (Dashboard) SI requiere admin/contigo123
            .anyRequest().authenticated()
        )
       
        .httpBasic(Customizer.withDefaults());

    return http.build();
}

    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        // Usuario inicial para que el contador pueda acceder al Dashboard
        var user = User.withUsername("admin")
                       .password(encoder.encode("contigo123")) 
                       .roles("ADMIN")
                       .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  @Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*")); 
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "x-auth-token"));
    configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

}