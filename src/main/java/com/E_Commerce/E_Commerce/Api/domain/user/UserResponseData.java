package com.E_Commerce.E_Commerce.Api.domain.user;

public record UserResponseData(
        Long id,
        String name,
        String email,
        Role role
) {
}
