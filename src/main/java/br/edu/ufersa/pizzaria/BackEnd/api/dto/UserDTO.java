package br.edu.ufersa.pizzaria.backend.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

    public record AuthRequest(
        @Email @Column(unique = true) String email,
        @NotNull String password
    ) {
    }

    public record EmailRequest(
        @Email @Column(unique = true) String email
    ) {
    }

    public record PasswordRequest(
        @NotNull @Size(min = 8) String password
    ) {
    }

    public record TokenDto(
        String token
    ) {
    }

    // User Delivery DTO's

    public record CreateClientDelivery(
        @Email @Column(unique = true) String email,
        String password,
        String phone,
        @NotNull String address
    ) {
    }
}