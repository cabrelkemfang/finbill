package io.growtogether.com.finbill.application.adapter.mysql.configuration;

import io.growtogether.com.finbill.application.adapter.mysql.jpaRepository.MysqlClientRepository;
import io.growtogether.com.finbill.application.adapter.mysql.mapper.ClientEntityMapper;
import io.growtogether.com.finbill.application.adapter.mysql.service.JpaClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {MysqlClientRepository.class})
@ComponentScan(basePackages = "io.growtogether.com.adapter.mysql.mapper")
public class MysqlAdapterConfig {

    @Bean
    public JpaClientService jpaClientService(MysqlClientRepository mysqlClientRepository,
                                             ClientEntityMapper clientEntityMapper) {
        return new JpaClientService(mysqlClientRepository, clientEntityMapper);
    }
}
