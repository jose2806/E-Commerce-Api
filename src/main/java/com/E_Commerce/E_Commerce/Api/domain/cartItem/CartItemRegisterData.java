package com.E_Commerce.E_Commerce.Api.domain.cartItem;

import jakarta.validation.constraints.NotNull;

public record CartItemRegisterData(
        @NotNull
        Long userId,
        @NotNull
        Long productId,
        @NotNull
        int quantity
) {
}

