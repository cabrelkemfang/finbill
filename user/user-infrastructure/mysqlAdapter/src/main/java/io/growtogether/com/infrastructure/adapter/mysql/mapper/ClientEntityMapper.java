package io.growtogether.com.infrastructure.adapter.mysql.mapper;

import io.growtogether.com.finbill.user.domain.model.Client;
import io.growtogether.com.finbill.user.domain.model.Email;
import io.growtogether.com.finbill.user.domain.model.PhoneNumber;
import io.growtogether.com.infrastructure.adapter.mysql.entity.ClientJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientEntityMapper {

    @Mapping(target = "id", source = "clientId.id")
    @Mapping(target = "email", source = "email.email")
    @Mapping(target = "phoneNumber", source = "phoneNumber.number")
    ClientJpaEntity mapToClientEntity(Client client);


    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    Client mapToClient(ClientJpaEntity clientJpaEntity);

    default Email stringToEmail(String email) {
        return new Email(email);
    }

    default PhoneNumber stringToPhoneNumber(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }
}
