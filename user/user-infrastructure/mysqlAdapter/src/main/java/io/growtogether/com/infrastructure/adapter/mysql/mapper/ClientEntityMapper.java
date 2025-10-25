package io.growtogether.com.infrastructure.adapter.mysql.mapper;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.ClientId;
import io.growtogether.com.finbill.user.domain.model.Email;
import io.growtogether.com.finbill.user.domain.model.PhoneNumber;
import io.growtogether.com.infrastructure.adapter.mysql.entity.ClientJpaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ClientEntityMapper {

    default ClientJpaEntity mapToClientEntity(Client client) {
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(client.id().id());
        entity.setFirstName(client.firstNameValue());
        entity.setLastName(client.lastNameValue());
        entity.setEmail(client.emailAddress().email());
        entity.setPhoneNumber(client.contactNumber().number());
        entity.setStatus(client.currentStatus());
        entity.setCreatedDate(client.creationDate());
        entity.setUpdatedDate(client.lastModifiedDate());
        return entity;
    }

    default Client mapToClient(ClientJpaEntity entity) {
        return Client.reconstruct(
                new ClientId(entity.getId()),
                entity.getFirstName(),
                entity.getLastName(),
                new Email(entity.getEmail()),
                new PhoneNumber(entity.getPhoneNumber()),
                entity.getStatus(),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
        );
    }
}
