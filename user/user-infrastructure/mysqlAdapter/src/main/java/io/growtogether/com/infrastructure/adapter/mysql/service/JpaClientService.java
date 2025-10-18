package io.growtogether.com.infrastructure.adapter.mysql.service;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.repository.ClientRepositoryPort;
import io.growtogether.com.infrastructure.adapter.mysql.jpaRepository.MysqlClientRepository;
import io.growtogether.com.infrastructure.adapter.mysql.mapper.ClientEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public PaginatedResponse<Client> findAll(int page, int size) {
        var clientPage = mysqlClientRepository.findAll(PageRequest.of(page - 1, size))
                .map(clientEntityMapper::mapToClient);

        return PaginatedResponse.<Client>builder()
                .content(clientPage.getContent())
                .totalElements(clientPage.getTotalElements())
                .totalPages(clientPage.getTotalPages())
                .currentPage(clientPage.getNumber() + 1)
                .build();
    }
}