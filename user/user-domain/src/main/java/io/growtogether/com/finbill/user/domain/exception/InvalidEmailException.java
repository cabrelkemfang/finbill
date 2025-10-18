package io.growtogether.com.finbill.user.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("The email provided is invalid: " + email);
    }
}
