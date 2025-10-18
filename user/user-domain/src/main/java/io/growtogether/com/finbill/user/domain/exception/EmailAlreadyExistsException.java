package io.growtogether.com.finbill.user.domain.exception;

import io.growtogether.com.finbill.user.domain.model.Email;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(Email email) {
        super("The email provided already exists: " + email);
    }
}
