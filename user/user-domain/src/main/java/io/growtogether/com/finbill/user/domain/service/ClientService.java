package io.growtogether.com.finbill.user.domain.service;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.Email;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;

import java.util.UUID;

public interface ClientService {
    UUID registerClient(Client client);

    PaginatedResponse<Client> findClients(int page, int size);

    Client findClientByEmail(Email email);
}
