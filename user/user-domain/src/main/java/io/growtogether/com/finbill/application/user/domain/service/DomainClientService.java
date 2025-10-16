package io.growtogether.com.finbill.application.user.domain.service;

import io.growtogether.com.finbill.application.user.domain.exception.ClientNotFoundException;
import io.growtogether.com.finbill.application.user.domain.exception.EmailAlreadyExistsException;
import io.growtogether.com.finbill.application.user.domain.model.Client;
import io.growtogether.com.finbill.application.user.domain.model.Email;
import io.growtogether.com.finbill.application.user.domain.repository.ClientRepositoryPort;

import java.util.List;
import java.util.UUID;

public class DomainClientService {

    private final ClientRepositoryPort clientRepositoryPort;

    public DomainClientService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    public UUID registerClient(Client client) {
        if (clientRepositoryPort.existsByEmail(client.getEmail().email())) {
            throw new EmailAlreadyExistsException(client.getEmail());
        }
        return clientRepositoryPort.save(client);
    }

    public List<Client> listClients(int page, int size) {
        return clientRepositoryPort.findAll(page, size);
    }

    public Client findClientByEmail(Email email) {
        return clientRepositoryPort.findByEmail(email.email())
                .orElseThrow(() -> new ClientNotFoundException(email));
    }
}
