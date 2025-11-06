package com.mcp.newmcp.configuration;

import org.springaicommunity.mcp.security.server.config.McpServerOAuth2Configurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                            .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                // Enforce authentication with token on EVERY request
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                // Configure OAuth2 on the MCP server
                .with(
                        McpServerOAuth2Configurer.mcpServerOAuth2(),
                        (mcpAuthorization) -> {
                            // REQUIRED: the issuerURI
                            mcpAuthorization.authorizationServer(issuer);
                            // OPTIONAL: enforce the `aud` claim in the JWT token.
                            // Not all authorization servers support resource indicators,
                            // so it may be absent. Defaults to `false`.
                            // See RFC 8707 Resource Indicators for OAuth 2.0
                            // https://www.rfc-editor.org/rfc/rfc8707.html
//                            mcpAuthorization.validateAudienceClaim(true);
                        }
                )
                .build();
    }
}
