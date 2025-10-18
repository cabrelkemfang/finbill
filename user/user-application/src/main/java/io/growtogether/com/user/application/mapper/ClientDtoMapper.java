package io.growtogether.com.user.application.mapper;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.Email;
import io.growtogether.com.finbill.user.domain.model.PaginatedResponse;
import io.growtogether.com.finbill.user.domain.model.PhoneNumber;
import io.growtogether.com.user.application.request.ClientRequest;
import io.growtogether.com.user.application.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientDtoMapper {
    @Mapping(target = "email", source = "email.email")
    @Mapping(target = "phoneNumber", source = "phoneNumber.number")
    @Mapping(target = "id", source = "clientId.id")
    ClientResponse mapToClientResponse(Client client);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    Client mapToClient(ClientRequest clientRequest);

    PaginatedResponse<ClientResponse> mapToPaginatedClientResponse(PaginatedResponse<Client> source);

    default Email stringToEmail(String email) {
        return new Email(email);
    }

    default PhoneNumber stringToPhoneNumber(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }
}
