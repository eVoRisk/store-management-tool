package com.store.management.tool.security;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        LOGGER.warn("Authentication failed");

        writeAuthFailureToResponse(response, SC_UNAUTHORIZED, authException.getMessage());
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        LOGGER.warn("User is not authorized to access {}", request.getServletPath());

        writeAuthFailureToResponse(response, SC_FORBIDDEN, accessDeniedException.getMessage());
    }

    private void writeAuthFailureToResponse(final HttpServletResponse response, final Integer status, final String message) {
        response.setStatus(status);
        response.setContentType(APPLICATION_JSON_VALUE);

        try (final ServletOutputStream os = response.getOutputStream()) {
            os.print(message);
            os.flush();
        } catch (final IOException e) {
            LOGGER.warn("Failed to write unauthorized response", e);
        }
    }
}
