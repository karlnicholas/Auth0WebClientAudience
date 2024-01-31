package org.example.auth0webclientaudience;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.endpoint.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

    @Configuration
    public class WebClientConfiguration {
        @Value("${spring.security.oauth2.resourceserver.jwt.audiences}")
        private String audience;

        @Bean
        public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
            ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                    new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

            return WebClient.builder()
                    .apply(oauth2Client.oauth2Configuration())
                    .baseUrl("http://localhost:8080/")
                    .build();
        }

        @Bean
        public OAuth2AuthorizedClientManager authorizedClientManager(
                ClientRegistrationRepository clientRegistrationRepository,
                OAuth2AuthorizedClientService authorizedClientService) {

            OAuth2AuthorizedClientProvider authorizedClientProvider =
                    OAuth2AuthorizedClientProviderBuilder.builder()
                            .clientCredentials((builder) ->
                                    builder.accessTokenResponseClient(clientCredentialsAccessTokenResponseClient())
                                            .build())
                            .build();

            AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                    new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                            clientRegistrationRepository, authorizedClientService);
            authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

            return authorizedClientManager;
        }

        private OAuth2AccessTokenResponseClient<OAuth2ClientCredentialsGrantRequest> clientCredentialsAccessTokenResponseClient() {
            DefaultClientCredentialsTokenResponseClient accessTokenResponseClient =
                    new DefaultClientCredentialsTokenResponseClient();

            OAuth2ClientCredentialsGrantRequestEntityConverter requestEntityConverter =
                    new OAuth2ClientCredentialsGrantRequestEntityConverter();
            requestEntityConverter.addParametersConverter(source -> CollectionUtils.toMultiValueMap(Collections.singletonMap("audience", Collections.singletonList(audience))));
            accessTokenResponseClient.setRequestEntityConverter(requestEntityConverter);

            return accessTokenResponseClient;
        }

    }
