package com.store.management.tool.config;

import com.store.management.tool.security.ApiAuthenticationFilter;
import com.store.management.tool.security.SecurityExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

    private static final String[] AUTHENTICATED_PATTERNS = {"/store/api/**"};
    private static final String[] ALLOWED_PATTERNS = {"/actuator/health", "/api/version"};

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity,
                                           final ApiAuthenticationFilter authenticationFilter,
                                           final SecurityExceptionHandler exceptionHandler) throws Exception {
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authenticationFilter)
                .securityMatcher("/store/**")
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(ALLOWED_PATTERNS).permitAll()
                                .requestMatchers(AUTHENTICATED_PATTERNS).authenticated())
                .exceptionHandling(exceptionHandling -> {
                            exceptionHandling
                                    .accessDeniedHandler(exceptionHandler);
                            exceptionHandling
                                    .authenticationEntryPoint(exceptionHandler);
                        }
                );

        return httpSecurity.build();
    }
}
