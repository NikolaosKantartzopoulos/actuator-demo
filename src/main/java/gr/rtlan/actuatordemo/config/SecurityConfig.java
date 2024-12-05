package gr.rtlan.actuatordemo.config;

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

    private static final String NOTE_ENDPOINTS = "/api/note/**";
    private static final String[] SWAGGER_ENDPOINTS = {
        "/swagger-ui/**",
        "/v3/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                auth.requestMatchers("/actuator/**").permitAll();
                auth.requestMatchers(NOTE_ENDPOINTS).permitAll();
                auth.requestMatchers("/api/**").permitAll();
                auth.requestMatchers(SWAGGER_ENDPOINTS).permitAll();
                auth.anyRequest().permitAll();
            });
        return http.build();
    }
}
