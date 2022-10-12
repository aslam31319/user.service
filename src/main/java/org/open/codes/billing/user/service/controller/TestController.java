package org.open.codes.billing.user.service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/user-service")
public class TestController {

    @GetMapping("/token")
    Mono<String> getToken(Principal principal) {
        JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) principal;
        Jwt jwtPrincipal = (Jwt) jwtAuthToken.getPrincipal();
        return Mono.just(jwtPrincipal.getTokenValue());
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('user_read')")
    Mono<String> getRoles(Principal principal) {
        JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) principal;
        return Mono.just(jwtAuthToken.getAuthorities().toString());
    }


}
