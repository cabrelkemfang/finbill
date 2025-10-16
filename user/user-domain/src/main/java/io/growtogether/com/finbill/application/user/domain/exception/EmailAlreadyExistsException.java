package io.growtogether.com.finbill.application.user.domain.exception;

import io.growtogether.com.finbill.application.user.domain.model.Email;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(Email email) {
        super("The email provided already exists: " + email);
    }
}
