package io.growtogether.com.finbill.user.domain.service;

import io.growtogether.com.finbill.user.domain.annotation.DomainService;
import io.growtogether.com.finbill.user.domain.exception.ClientNotFoundException;
import io.growtogether.com.finbill.user.domain.exception.EmailAlreadyExistsException;
import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.Email;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.repository.ClientRepositoryPort;

import java.util.UUID;

@DomainService
class DomainClientService implements ClientService {

    private final ClientRepositoryPort clientRepositoryPort;

    public DomainClientService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public UUID registerClient(Client client) {
        if (clientRepositoryPort.existsByEmail(client.getEmail().email())) {
            throw new EmailAlreadyExistsException(client.getEmail());
        }
        return clientRepositoryPort.save(client);
    }

    @Override
    public PaginatedResponse<Client> findClients(int page, int size) {
        return clientRepositoryPort.findAll(page, size);
    }

    @Override
    public Client findClientByEmail(Email email) {
        return clientRepositoryPort.findByEmail(email.email())
                .orElseThrow(() -> new ClientNotFoundException(email));
    }
}
