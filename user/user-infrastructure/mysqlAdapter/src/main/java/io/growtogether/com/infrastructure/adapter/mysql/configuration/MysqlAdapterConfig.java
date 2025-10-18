package io.growtogether.com.infrastructure.adapter.mysql.configuration;

import io.growtogether.com.infrastructure.adapter.mysql.entity.ClientJpaEntity;
import io.growtogether.com.infrastructure.adapter.mysql.jpaRepository.MysqlClientRepository;
import io.growtogether.com.infrastructure.adapter.mysql.mapper.ClientEntityMapper;
import io.growtogether.com.infrastructure.adapter.mysql.service.JpaClientService;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {MysqlClientRepository.class})
@AutoConfigurationPackage(basePackageClasses = ClientJpaEntity.class)
@ComponentScan(basePackageClasses = ClientEntityMapper.class)
public class MysqlAdapterConfig {

    @Bean
    public JpaClientService jpaClientService(MysqlClientRepository mysqlClientRepository,
                                             ClientEntityMapper clientEntityMapper) {
        return new JpaClientService(mysqlClientRepository, clientEntityMapper);
    }
}
