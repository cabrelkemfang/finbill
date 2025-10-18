package io.growtogether.com.finbill.user.domain.repository;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepositoryPort {

    boolean existsByEmail(String email);

    Optional<Client> findByEmail(String email);

    UUID save(Client client);

    PaginatedResponse<Client> findAll(int page, int size);
}
