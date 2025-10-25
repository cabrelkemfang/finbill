package io.growtogether.com.finbill.user.domain.model;

import java.time.LocalDateTime;

public class Client {
    private final ClientId clientId;
    private final String firstName;
    private final String lastName;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final ClientStatus status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private Client(String firstName, String lastName, Email email, PhoneNumber phoneNumber) {
        this.clientId = new ClientId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = ClientStatus.ACTIVE;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    private Client(ClientId clientId, String firstName, String lastName, Email email, PhoneNumber phoneNumber, ClientStatus status, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static Client create(String firstName, String lastName, Email email, PhoneNumber phoneNumber) {
        return new Client(firstName, lastName, email, phoneNumber);
    }

    public static Client reconstruct(ClientId clientId, String firstName, String lastName, Email email, PhoneNumber phoneNumber, ClientStatus status, LocalDateTime createdDate, LocalDateTime updatedDate) {
        return new Client(clientId, firstName, lastName, email, phoneNumber,status, createdDate, updatedDate);
    }

    public ClientId id() {
        return clientId;
    }

    public Email emailAddress() {
        return email;
    }

    public PhoneNumber contactNumber() {
        return phoneNumber;
    }

    public ClientStatus currentStatus() {
        return status;
    }

    public LocalDateTime creationDate() {
        return createdDate;
    }

    public LocalDateTime lastModifiedDate() {
        return updatedDate;
    }

    public String firstNameValue() {
        return firstName;
    }

    public String lastNameValue() {
        return lastName;
    }
}
