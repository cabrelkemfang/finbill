package io.growtogether.com.user.application.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequest {
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @NotBlank(message = "First name is required")
    private final String firstName;
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @NotBlank(message = "Last name is required")
    private final String lastName;
    @NotBlank(message = "Email is required")
    private final @Email(message = "Email must be valid") String email;
    @NotBlank(message = "Phone number is required")
    private final String phoneNumber;
}