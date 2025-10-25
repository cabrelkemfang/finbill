package io.growtogether.com.finbill.application.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.auth.converter")
public record ConfigProperties(@NotNull String resourceId) {
}
