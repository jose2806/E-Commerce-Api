package com.E_Commerce.E_Commerce.Api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterData(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password,
        Role role
) {
}
