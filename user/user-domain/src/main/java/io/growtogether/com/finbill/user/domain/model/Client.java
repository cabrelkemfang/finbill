package io.growtogether.com.finbill.user.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Client {
    private final ClientId clientId;
    private final String firstName;
    private final String lastName;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final ClientStatus status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public Client(String firstName, String lastName, Email email, PhoneNumber phoneNumber) {
        this.clientId = new ClientId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = ClientStatus.ACTIVE;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
