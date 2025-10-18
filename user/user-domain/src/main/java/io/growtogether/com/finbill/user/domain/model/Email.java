package io.growtogether.com.finbill.user.domain.model;

import io.growtogether.com.finbill.user.domain.exception.InvalidEmailException;

public record Email(String email) {
    public Email {
        if (email.isBlank() || !isValid(email)) {
            throw new InvalidEmailException(email);
        }
    }

    private boolean isValid(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
