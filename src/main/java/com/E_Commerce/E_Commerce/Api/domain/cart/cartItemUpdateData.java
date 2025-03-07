package com.E_Commerce.E_Commerce.Api.domain.cart;

import com.E_Commerce.E_Commerce.Api.domain.product.Product;
import com.E_Commerce.E_Commerce.Api.domain.user.User;

public record cartItemUpdateData(
        Long userId,
        Long productId,
        int quantity
) {
}
