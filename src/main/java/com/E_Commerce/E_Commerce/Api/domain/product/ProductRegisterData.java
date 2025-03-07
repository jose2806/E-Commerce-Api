package com.E_Commerce.E_Commerce.Api.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRegisterData(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        @PositiveOrZero
        Double price,
        @NotNull
        @PositiveOrZero
        int stock
) {
}
