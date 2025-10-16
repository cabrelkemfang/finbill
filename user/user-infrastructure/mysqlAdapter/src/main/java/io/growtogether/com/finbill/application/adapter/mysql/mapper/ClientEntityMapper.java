package io.growtogether.com.finbill.application.adapter.mysql.mapper;

import io.growtogether.com.finbill.application.adapter.mysql.entity.ClientJpaEntity;
import io.growtogether.com.finbill.application.user.domain.model.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientEntityMapper {

    @Mapping(target = "id", expression = "clientId.id()")
    @Mapping(target = "email", expression = "email.email()")
    @Mapping(target = "phoneNumber", expression = "phoneNumber.number()")
    ClientJpaEntity mapToClientEntity(Client client);

    @InheritInverseConfiguration
    Client mapToClient(ClientJpaEntity clientJpaEntity);
}

