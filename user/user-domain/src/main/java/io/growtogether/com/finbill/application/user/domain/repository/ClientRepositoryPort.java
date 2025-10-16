package io.growtogether.com.finbill.application.user.domain.repository;

import io.growtogether.com.finbill.application.user.domain.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepositoryPort {

    boolean existsByEmail(String email);

    Optional<Client> findByEmail(String email);

    UUID save(Client client);

    List<Client> findAll(int page, int size);
}
