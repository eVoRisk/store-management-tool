package com.store.management.tool.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class ApiAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Pattern AUTH_HEADER_PATTERN = Pattern.compile("Basic\\s+(.+)");

    public ApiAuthenticationFilter(final StoreAuthenticationManager storeAuthenticationManager) {
        this.setAuthenticationManager(storeAuthenticationManager);
        this.setSecurityContextRepository(new NullSecurityContextRepository());
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        return extractTokenHeaderValue(request.getHeader(AUTHORIZATION));
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

    private String extractTokenHeaderValue(final String authHeaderValue) {
        return ofNullable(authHeaderValue)
                .map(String::trim)
                .map(AUTH_HEADER_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(m -> m.group(1))
                .orElse(null);
    }
}
