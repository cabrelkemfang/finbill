package io.growtogether.com.finbill.application.adapter.mysql.service;

import io.growtogether.com.finbill.application.adapter.mysql.jpaRepository.MysqlClientRepository;
import io.growtogether.com.finbill.application.adapter.mysql.mapper.ClientEntityMapper;
import io.growtogether.com.finbill.application.user.domain.model.Client;
import io.growtogether.com.finbill.application.user.domain.repository.ClientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JpaClientService implements ClientRepositoryPort {
    private final MysqlClientRepository mysqlClientRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Override
    public boolean existsByEmail(String email) {
        return mysqlClientRepository.existsByEmail(email);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return mysqlClientRepository.findByEmail(email)
                .map(clientEntityMapper::mapToClient);
    }

    @Override
    public UUID save(Client client) {
        var clientJpaEntity = clientEntityMapper.mapToClientEntity(client);
        var clientEntity = mysqlClientRepository.save(clientJpaEntity);
        return clientEntity.getId();
    }

    @Override
    public List<Client> findAll(int page, int size) {
        return mysqlClientRepository.findAll()
                .stream()
                .map(clientEntityMapper::mapToClient)
                .toList();
    }
}
