package io.growtogether.com.user.application.configuration;

import io.growtogether.com.finbill.user.domain.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
        basePackages = {"io.growtogether.com.finbill.user",
                "io.growtogether.com.user.application.controller",
                "io.growtogether.com.user.application.mapper"},
        includeFilters = {@ComponentScan.Filter(type = ANNOTATION, classes = {DomainService.class})})
public class ApplicationConfig {
}
