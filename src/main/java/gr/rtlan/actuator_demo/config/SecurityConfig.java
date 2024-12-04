package gr.rtlan.actuator_demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(prePostEnabled = true)
@Profile("!dev")
public class SecurityConfig {

    private static final String NOTE_ENDPOINTS = "/api/note";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(HttpMethod.OPTIONS).permitAll();
                auth.requestMatchers("/actuator/**").permitAll();
                auth.requestMatchers(HttpMethod.GET, NOTE_ENDPOINTS).permitAll();
                auth.requestMatchers(HttpMethod.POST, NOTE_ENDPOINTS).permitAll();
                auth.anyRequest().authenticated();
            });
        return http.build();
    }
}
