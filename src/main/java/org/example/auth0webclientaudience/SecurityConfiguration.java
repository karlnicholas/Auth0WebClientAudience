package org.example.auth0webclientaudience;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(auth -> auth
                            .anyRequest()
                            .authenticated())
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
        }
    }
