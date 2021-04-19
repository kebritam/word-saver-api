package com.teimour.wordsapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;

/**
 * @author kebritam
 * Project words-api
 * Created on 18/04/2021
 */

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(@Value("${secret}") String secret) {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(secret);
        encoder.setEncodeHashAsBase64(true);
        encoder.setAlgorithm(PBKDF2WithHmacSHA512);
        return encoder;
    }
}
