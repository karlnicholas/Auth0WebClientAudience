package org.example.auth0webclientaudience;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

    @RestController
    @RequestMapping
    public class MovieApi {
        @GetMapping("movies")
        public List<String> getMovies() {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (!(securityContext instanceof AnonymousAuthenticationToken)) {
                return List.of("movie1", "movie2");
            }
            return Collections.emptyList();
        }
    }
