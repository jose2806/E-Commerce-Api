package com.E_Commerce.E_Commerce.Api.domain.cartItem;

public record CartItemUpdateData(
        Long userId,
        Long productId,
        int quantity
) {
}
