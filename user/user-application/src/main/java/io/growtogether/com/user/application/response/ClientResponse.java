package io.growtogether.com.user.application.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ClientResponse {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
}

