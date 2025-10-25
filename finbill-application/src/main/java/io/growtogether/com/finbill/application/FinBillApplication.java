package io.growtogether.com.finbill.application;

import io.growtogether.com.finbill.application.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class FinBillApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinBillApplication.class, args);
    }
}
