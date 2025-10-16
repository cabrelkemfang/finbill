package io.growtogether.com.finbill.application.user.domain.exception;

import io.growtogether.com.finbill.application.user.domain.model.Email;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Email email) {
        super("Client with email " + email + " not found");
    }
}

