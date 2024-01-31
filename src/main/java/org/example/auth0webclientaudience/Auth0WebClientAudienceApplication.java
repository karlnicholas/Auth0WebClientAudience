package org.example.auth0webclientaudience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

    @SpringBootApplication
    public class Auth0WebClientAudienceApplication implements ApplicationRunner {

        public static void main(String[] args) {
            SpringApplication.run(Auth0WebClientAudienceApplication.class, args);
        }

        @Autowired
        WebClient webClient;
        @Override
        public void run(ApplicationArguments args) {
            List<String> r = webClient.get().uri("movies")
                    .attributes(clientRegistrationId("auth0"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<String>>() {
                    }).block();
            System.out.println("movies = " + r);
        }
    }
