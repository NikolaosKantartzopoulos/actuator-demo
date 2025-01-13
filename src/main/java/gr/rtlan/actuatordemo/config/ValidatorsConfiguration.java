package gr.rtlan.actuatordemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.rtlan.actuatordemo.validator.NoteBodyValidator;

@Configuration
public class ValidatorsConfiguration {
    @Bean
    public NoteBodyValidator noteBodyValidator() {
        return new NoteBodyValidator();
    }
}
