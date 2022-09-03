package com.edenlifemock.customer.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
@Data
public class JwtConfig {
    private final String secret = "jwt-tokens-that-should-be-changed-production";
    private final String tokenPrefix = "Bearer ";
    private final Long tokenExpirationAfterDays = 7L;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
