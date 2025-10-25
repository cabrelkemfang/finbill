package io.growtogether.com.infrastructure.adapter.mysql.service;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.repository.ClientRepositoryPort;
import io.growtogether.com.infrastructure.adapter.mysql.jpaRepository.MysqlClientRepository;
import io.growtogether.com.infrastructure.adapter.mysql.mapper.ClientEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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
        var savedEntity = mysqlClientRepository.save(clientJpaEntity);
        log.info("Client saved with ID: {}", savedEntity.getId());
        return savedEntity.getId();
    }

    @Override
    public PaginatedResponse<Client> findAll(int page, int size) {
        var pageRequest = PageRequest.of(page - 1, size);
        var clientPage = mysqlClientRepository.findAll(pageRequest)
                .map(clientEntityMapper::mapToClient);

        return PaginatedResponse.<Client>builder()
                .content(clientPage.getContent())
                .totalElements(clientPage.getTotalElements())
                .totalPages(clientPage.getTotalPages())
                .currentPage(clientPage.getNumber() + 1)
                .pageSize(clientPage.getSize())
                .build();
    }
}