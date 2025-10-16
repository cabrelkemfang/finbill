package io.growtogether.com.finbill.application.adapter.mysql.jpaRepository;

import io.growtogether.com.finbill.application.adapter.mysql.entity.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MysqlClientRepository extends JpaRepository<ClientJpaEntity, UUID> {

    Optional<ClientJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
