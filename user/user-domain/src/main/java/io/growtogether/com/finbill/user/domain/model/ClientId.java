package io.growtogether.com.finbill.user.domain.model;

import java.util.UUID;

public record ClientId(UUID id) {

    public ClientId() {
        this(UUID.randomUUID());
    }
}
