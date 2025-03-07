package com.E_Commerce.E_Commerce.Api.domain.cart;

import com.E_Commerce.E_Commerce.Api.domain.user.User;
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

