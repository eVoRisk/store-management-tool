package com.store.management.tool.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class StoreAuthenticationManager implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreAuthenticationManager.class);
    private final Map<String, String> encodedSecurityTokens = new HashMap<>();

    public StoreAuthenticationManager(@Value("#{${api.security.tokens}}") final Map<String, String> securityTokens) {
        securityTokens
                .entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .forEach(e -> encodedSecurityTokens.put(e.getKey(),
                        Base64.getEncoder().encodeToString(e.getValue().getBytes())));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authToken = (String) authentication.getPrincipal();

        String foundKey = encodedSecurityTokens
                .entrySet()
                .stream()
                .filter(e -> e.getValue().equals(authToken))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new BadCredentialsException("Failed to authenticate request"));

        LOGGER.info("Request authorized for user={}", foundKey);
        authentication.setAuthenticated(true);

        return authentication;
    }
}
