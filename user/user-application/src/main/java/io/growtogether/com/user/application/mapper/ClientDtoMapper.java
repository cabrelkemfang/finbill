package io.growtogether.com.user.application.mapper;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.Email;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.model.PhoneNumber;
import io.growtogether.com.user.application.request.ClientRequest;
import io.growtogether.com.user.application.response.ClientResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ClientDtoMapper {

    default ClientResponse mapToClientResponse(Client client) {
        var email = client.emailAddress();
        var phoneNumber = client.contactNumber();
        var clientStatus = client.currentStatus();
        return new ClientResponse(
                client.id().id(),
                client.firstNameValue(),
                client.lastNameValue(),
                email.email(),
                phoneNumber.number(),
                clientStatus.toString(),
                client.creationDate(),
                client.lastModifiedDate()
        );
    }

    default Client mapToClient(ClientRequest clientRequest) {
        return Client.create(
                clientRequest.getFirstName(),
                clientRequest.getLastName(),
                new Email(clientRequest.getEmail()),
                new PhoneNumber(clientRequest.getPhoneNumber())
        );
    }

    PaginatedResponse<ClientResponse> mapToPaginatedClientResponse(PaginatedResponse<Client> client);
}
